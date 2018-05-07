package Bot.Service;

import Bot.DTO.Elements.*;
import Bot.DTO.Message.QuickReplyMessage;
import Bot.DTO.RequestDTO.Messaging;
import Bot.DTO.Template.*;
import Bot.Domain.HeroesRatingEntity;
import Bot.Domain.UserEntity;
import Bot.Repository.HeroesRatingRepository;
import Bot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class MessageService {

    private static final int maxCharacterNumberAtOne = 10;

    private static final String like = "\uD83D\uDC4D";

    private static final String dislike = "\uD83D\uDC4E";


    @Autowired
    private HeroesRatingRepository heroesRatingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageTypeDetector messageTypeDetector;

    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private ApiCaller apiCaller;

    @Value("${response.httpException}")
    private String httpExceptionMessage;

    @Value("${response.noTemplateInitialized}")
    private String noTemplateInitialized;

    private Long recepientId;

    public Long getRecepientId() {
        return recepientId;
    }

    public void setRecepientId(Long recepientId) {
        this.recepientId = recepientId;
    }

    public void processRequest(Messaging request) {
        MessageTemplate template;
        setRecepientId(request.getSender().getId());
        if (!messageTypeDetector.isText(request)) {
            template = messageHandler.handleNonTextMessage(request);
        } else {
            template = messageHandler.handleMessageWithText(request);
        }
        apiCaller.callSendAPI(template);

    }

    public QuickReplyMessage getHeroesForQuickReply(String text) {

        //Getting top rated heroes from database, and adding them into set of topHeroes
        //if there are less heroes than required in MaxHeroNumber filling in with hardcoded set of heroes

        List<QuickReply> quickReplies = new ArrayList<>();
        Set<String> topHeroes = heroesRatingRepository.getTopHeroesForQuickReply();
        topHeroes = chechTopHeroesSize(topHeroes, 0);
        for (String s : topHeroes) {
            quickReplies.add(new QuickReply("text", s, "hero"));
        }


        return new QuickReplyMessage(text, quickReplies);
    }

    public QuickReplyMessage getRatingQuickReply(String id) {
        //Sending quick reply message with like and dislike buttons
        String ratingMessage = "What do you think about this hero?";
        Long likes = heroesRatingRepository.getLikesForHero(id);
        Long dislikes = heroesRatingRepository.getDisLikesForHero(id);
        List<QuickReply> quickReplies = new ArrayList<>();
        quickReplies.add(new QuickReply("text", like + " " + likes, id));
        quickReplies.add(new QuickReply("text", dislike + " " + dislikes, id));
        return new QuickReplyMessage(ratingMessage, quickReplies);
    }

    public void rateHero(Messaging request) {
        //Hero Name, Sender ID , And hero rating are saving to database
        //If user didn't rate hero before, new rating is created,
        //Otherwise rating is rewritten

        String heroName = request.getMessage().getQuickReply().getPayload();
        Long senderPSID = request.getSender().getId();
        Boolean rating = request.getMessage().getText().startsWith(like);
        UserEntity user = userRepository.getBySenderPSID(senderPSID);
        HeroesRatingEntity heroesRatingEntity;

        if (heroesRatingRepository.getByHeroNameAndSenderPSID(heroName, senderPSID) == null) {
            heroesRatingEntity = new HeroesRatingEntity(heroName, senderPSID, rating);
//            heroesRatingEntity.setUser(user);
            heroesRatingRepository.save(heroesRatingEntity);
        } else {
            HeroesRatingEntity oldRating = heroesRatingRepository.getByHeroNameAndSenderPSID(heroName, senderPSID);
            Long ratingId = oldRating.getId();
            heroesRatingEntity = new HeroesRatingEntity(heroName, senderPSID, rating);
            heroesRatingEntity.setId(ratingId);
//            heroesRatingEntity.setUser(user);
            heroesRatingRepository.save(heroesRatingEntity);
        }
    }

    private List<String> fillInHeroQuickReplyes() {
        //Hardcoded Heroes for Quick reply if rating has less than MaxNumber of heroes
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
        //checking if there are enough heroes to build quick reply message
        if (topHeroes.size() < maxCharacterNumberAtOne) {
            List<String> heroQuickReplyFillIns = fillInHeroQuickReplyes();
            topHeroes.add(heroQuickReplyFillIns.get(index));
            chechTopHeroesSize(topHeroes, ++index);
        }
        return topHeroes;
    }
}

