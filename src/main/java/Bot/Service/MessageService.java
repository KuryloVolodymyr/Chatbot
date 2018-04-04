package Bot.Service;

import Bot.DTO.DialogFlowDTO.DialogFlowRequest.DialogFlowRequest;
import Bot.DTO.DialogFlowDTO.DialogFlowResponse.DialogFlowResponse;
import Bot.DTO.Elements.*;
import Bot.DTO.MarvelDTO.*;
import Bot.DTO.Message.QuickReplyMessage;
import Bot.DTO.Recipient;
import Bot.DTO.RequestDTO.Messaging;
import Bot.DTO.Template.*;
import Bot.Domain.HeroesRatingEntity;
import Bot.Domain.UserRequestEntity;
import Bot.Domain.UserSettingsEntity;
import Bot.Repository.HeroesRatingRepository;
import Bot.Repository.UserRequestRepository;
import Bot.Repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class MessageService {



    @Autowired
    private HeroesRatingRepository heroesRatingRepository;

    @Autowired
    private MessageTypeDetector messageTypeDetector;

    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private ApiCaller apiCaller;

    @Value("${responce.httpException}")
    private String httpExceptionMessage;

    @Value("${responce.noTemplateInitialized}")
    private String noTemplateInitialized;


    public void processRequest(Messaging request) {

        long id = request.getSender().getId();
        MessageTemplate template = new TextMessageTemplate(id, noTemplateInitialized);

        if (!messageTypeDetector.isText(request)) {
            System.out.println("in !text");
            if (messageTypeDetector.isImage(request)) {
                System.out.println("is image");
                template = messageHandler.handleImageMessage(request);
            } else if (messageTypeDetector.isStart(request)) {
                System.out.println("Start");
                template = messageHandler.handleGreeting(request);
            } else if (messageTypeDetector.isHelp(request)) {
                System.out.println("Help");
                template = messageHandler.handleHelpTemplate(request);
            } else if (messageTypeDetector.isTop(request)) {
                System.out.println("Top");
                template = messageHandler.handleTopTemplate(request);
            } else if (messageTypeDetector.isChangeComicsAmound(request)) {
                template = messageHandler.handleChangeComicsAtOnce(request);
            } else if (messageTypeDetector.isGetComics(request)) {
                System.out.println("get comics");
                template = messageHandler.handleComicsTemplate(request);
            } else if (messageTypeDetector.isMoreComics(request)) {
                System.out.println("more comics");
                template = messageHandler.handleMoreComics(request);
            } else if (messageTypeDetector.isRate(request)) {
                System.out.println("rating");
                template = new QuickReplyTemplate(id, getRatingQuickReply(request.getPostback().getPayload()));
            }
        } else {
            if (messageTypeDetector.isQuickReply(request)) {
                System.out.println("quick reply");
                if (messageTypeDetector.isHeroQuickReply(request)) {
                    System.out.println("hero quick reply");
                    template = messageHandler.handleGreetingQuickReply(request);
                } else if (messageTypeDetector.isRatingQuickReply(request)) {
                    System.out.println("reting quich reply");
                    rateHero(request);
                    template = messageHandler.handleRatingTemplate(request);
                } else {
                    System.out.println("Quick reply not trigered");
                }
            } else {
                template = messageHandler.handleTextMessage(request);
            }
        }
        try {
            System.out.println("calling send Api OK");
            apiCaller.callSendAPI(template);
        } catch (HttpClientErrorException e) {

            System.out.println("calling send Api Escepltion");
            apiCaller.callSendAPI(new TextMessageTemplate(request.getSender().getId(), httpExceptionMessage));
        }
    }

    public QuickReplyMessage getHeroesForQuickReply(String text) {

        List<QuickReply> quickReplies = new ArrayList<>();
        Set<String> topHeroes = heroesRatingRepository.getTopHeroesForQuickReply();
        for (String hero : topHeroes) {
            System.out.println(hero);
        }
        topHeroes = chechTopHeroesSize(topHeroes, 0);
        for (String s : topHeroes) {
            quickReplies.add(new QuickReply("text", s, "hero"));
            System.out.println(s);
        }


        return new QuickReplyMessage(text, quickReplies);
    }

    private QuickReplyMessage getRatingQuickReply(String id) {
        String ratingMessage = "What do you think about this hero?";
        List<QuickReply> quickReplies = new ArrayList<>();
        quickReplies.add(new QuickReply("text", "\uD83D\uDC4D", id));
        quickReplies.add(new QuickReply("text", "\uD83D\uDC4E", id));
        return new QuickReplyMessage(ratingMessage, quickReplies);
    }

    private void rateHero(Messaging request) {
        String heroName = request.getMessage().getQuickReply().getPayload();
        Long senderPSID = request.getSender().getId();
        String rating = request.getMessage().getText();

        if (heroesRatingRepository.getByHeroNameAndSenderPSID(heroName, senderPSID) == null) {
            heroesRatingRepository.save(new HeroesRatingEntity(heroName, senderPSID, rating));
        } else {
            HeroesRatingEntity oldRating = heroesRatingRepository.getByHeroNameAndSenderPSID(heroName, senderPSID);
            Long ratingId = oldRating.getId();
            HeroesRatingEntity newRating = new HeroesRatingEntity(heroName, senderPSID, rating);
            newRating.setId(ratingId);
            heroesRatingRepository.save(newRating);
        }
    }

    private List<String> fillInHeroQuickReplyes() {
        List<String> fillInHeroQuickReplyes = new ArrayList<>();
        fillInHeroQuickReplyes.add("Iron Man");
        fillInHeroQuickReplyes.add("Hulk");
        fillInHeroQuickReplyes.add("Spider-Man");
        fillInHeroQuickReplyes.add("Sandman");
        fillInHeroQuickReplyes.add("Captain America");
        fillInHeroQuickReplyes.add("Thor");
        fillInHeroQuickReplyes.add("Vision");
        fillInHeroQuickReplyes.add("Star-Lord");
        fillInHeroQuickReplyes.add("Doctor Strange");
        fillInHeroQuickReplyes.add("Deadpool");
        return fillInHeroQuickReplyes;
    }

    private Set<String> chechTopHeroesSize(Set<String> topHeroes, Integer index) {
        if (topHeroes.size() < 10) {
            List<String> heroQuickReplyFillIns = fillInHeroQuickReplyes();
            topHeroes.add(heroQuickReplyFillIns.get(index));
            chechTopHeroesSize(topHeroes, index + 1);
        }
        return topHeroes;
    }
}

