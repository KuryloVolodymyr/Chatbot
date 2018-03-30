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
    private RestTemplate restTemplate;

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private HeroesRatingRepository heroesRatingRepository;

    @Autowired
    private UserSettingsRepository userSettingsRepository;

    @Autowired
    private MarvelTemplateBuilder marvelTemplateBuilder;

    @Autowired
    private MessageTypeDetector messageTypeDetector;

    @Value("${pageAccessToken}")
    private String pageAccessToken;

    @Value("${marvel.privateKey}")
    private String marvelPrivateKey;

    @Value("${marvel.publicKey}")
    private String marvelPublicKey;

    @Value("${dialogFlow.clientAccessToken}")
    private String dialogFlowClientToken;

    @Value("${dialogFlow.developerAccessToken}")
    private String dialogFlowDeveloperToken;

    @Value("${responce.greeting}")
    private String greeting;

    @Value("${responce.herNotFound}")
    private String heroNotFound;

    @Value("${responce.noTemplateInitialized}")
    private String noTemplateInitialized;

    @Value("${responce.noComicsFound}")
    private String noComicsFound;

    @Value("${responce.cantFindHeroName}")
    private String cantFindHeroName;

    @Value("${responce.handleImage}")
    private String imageHandleMessage;

    @Value("${responce.httpException}")
    private String httpExceptionMessage;

    @Value("${responce.ratingReply}")
    private String ratingReply;

    @Value("${responce.help}")
    private String helpMessage;

    @Value("${responce.topHeroes}")
    private String topHeroes;

    @Value("${responce.settingsChanged}")
    private String settingsChanged;


    public void processRequest(Messaging request) {

        long id = request.getSender().getId();
        MessageTemplate template = new TextMessageTemplate(id, noTemplateInitialized);

        if (!messageTypeDetector.isText(request)) {
            System.out.println("in !text");
            if (messageTypeDetector.isImage(request)) {
                System.out.println("is image");
                template = handleImageMessage(request);
            } else if (messageTypeDetector.isStart(request)) {
                System.out.println("Start");
                template = handleGreeting(request);
            } else if (messageTypeDetector.isHelp(request)) {
                System.out.println("Help");
                template = handleHelpTemplate(request);
            } else if (messageTypeDetector.isTop(request)) {
                System.out.println("Top");
                template = handleTopTemplate(request);
            } else if (messageTypeDetector.isChangeComicsAmound(request)) {
                template = handleChangeComicsAtOnce(request);
            } else if (messageTypeDetector.isGetComics(request)) {
                System.out.println("get comics");
                template = handleComicsTemplate(request);
            } else if (messageTypeDetector.isMoreComics(request)) {
                System.out.println("more comics");
                template = handleMoreComics(request);
            } else if (messageTypeDetector.isRate(request)) {
                System.out.println("rating");
                template = new QuickReplyTemplate(id, getRatingQuickReply(request.getPostback().getPayload()));
            }
        } else {
            if (messageTypeDetector.isQuickReply(request)) {
                System.out.println("quick reply");
                if (messageTypeDetector.isHeroQuickReply(request)) {
                    System.out.println("hero quick reply");
                    template = handleGreetingQuickReply(request);
                } else if (messageTypeDetector.isRatingQuickReply(request)) {
                    System.out.println("reting quich reply");
                    rateHero(request);
                    template = handleRatingTemplate(request);
                } else {
                    System.out.println("Quick reply not trigered");
                }
            } else {
                template = handleTextMessage(request);
            }
        }
        try {
            System.out.println("calling send Api OK");
            callSendAPI(template);
        } catch (HttpClientErrorException e) {

            System.out.println("calling send Api Escepltion");
            callSendAPI(new TextMessageTemplate(request.getSender().getId(), httpExceptionMessage));
        }
    }

    private void callSendAPI(MessageTemplate message) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(message, headers);

        String responceFromSendAPI = restTemplate.postForObject("https://graph.facebook.com/v2.6/me/messages?access_token={token}",
                entity, String.class, pageAccessToken);
        System.out.println(responceFromSendAPI);

        System.out.println("OK");
    }

    private MarvelCharacterlResponse callMarvelAPIForChatacter(String characterName) throws HttpClientErrorException {
        MarvelCharacterlResponse marvelCharacterlResponse;

        String ts = new Timestamp(System.currentTimeMillis()).toString();
        String limit = "5";

        String hash = createHashForCallMarvelApi(ts);

        marvelCharacterlResponse = restTemplate.getForObject("https://gateway.marvel.com:443/v1/public/characters?name={name}&limit={limit}&ts={ts}&apikey={key}&hash={hash}",
                MarvelCharacterlResponse.class, characterName, limit, ts, marvelPublicKey, hash);

        System.out.println("Marvel called successfully 1st time");
        if (marvelCharacterlResponse.getData().getResults().isEmpty()) {
            System.out.println("Is empty");
            String newTs = new Timestamp(System.currentTimeMillis()).toString();
            String newHash = createHashForCallMarvelApi(newTs);
            System.out.println("Calling Marvel 2nd time");
            try {
                marvelCharacterlResponse = restTemplate.getForObject("https://gateway.marvel.com:443/v1/public/characters?nameStartsWith={name}&limit={limit}&ts={new}&apikey={key}&hash={newhash}",
                        MarvelCharacterlResponse.class, characterName, limit, newTs, marvelPublicKey, newHash);
                System.out.println("2nd call is successfull");
            } catch (HttpClientErrorException e) {
                System.out.println(e.getLocalizedMessage());
            }

        }
        System.out.println("Responce");
        return marvelCharacterlResponse;
    }

    private MarvelComicsResponce callMarvelAPIForComics(String characterId, Long limit) throws HttpClientErrorException {
        String ts = new Timestamp(System.currentTimeMillis()).toString();

        String hash = createHashForCallMarvelApi(ts);

        return restTemplate.getForObject("https://gateway.marvel.com:443/v1/public/characters/{characterId}/comics?orderBy=-focDate&limit={limit}&ts={ts}&apikey={key}&hash={hash}",
                MarvelComicsResponce.class, characterId, limit, ts, marvelPublicKey, hash);

    }

    private MarvelComicsResponce callMarvelAPIForComics(String characterId, Long limit, String offset) throws HttpClientErrorException {
        String ts = new Timestamp(System.currentTimeMillis()).toString();

        String hash = createHashForCallMarvelApi(ts);

        return restTemplate.getForObject("https://gateway.marvel.com:443/v1/public/characters/{characterId}/comics?&limit={limit}&offset={offset}&ts={ts}&apikey={key}&hash={hash}",
                MarvelComicsResponce.class, characterId, limit, offset, ts, marvelPublicKey, hash);

    }

    private DialogFlowResponse callDialogFlowApi(DialogFlowRequest dialogFlowRequest) throws HttpClientErrorException {
        String url = "https://api.dialogflow.com/v1/query?v=20150910";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + dialogFlowClientToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(dialogFlowRequest, headers);
        return restTemplate.postForObject(url, entity, DialogFlowResponse.class);
    }

    private MessageTemplate handleImageMessage(Messaging request) {

//        String url = request.getMessage().getAttachments().get(0).getPayload().getUrl();
//
//        ImagePayload payload = new ImagePayload(url);
//        ImageAttachment imageAttachment = new ImageAttachment(payload);
//        ImageMessage imageMessage = new ImageMessage(imageAttachment);
//
//        return new ImageTemplate(recipient, imageMessage);

        return new TextMessageTemplate(request.getSender().getId(), imageHandleMessage);
    }

    private MessageTemplate handleGreeting(Messaging request) {
        Long senderPSID = request.getSender().getId();
        if (userSettingsRepository.getBySenderPSID(senderPSID) == null) {
            userSettingsRepository.save(new UserSettingsEntity(senderPSID));
        }
        return new QuickReplyTemplate(senderPSID, getHeroesForQuickReply(greeting));
    }

    private MessageTemplate handleTextMessage(Messaging request) {
        MessageTemplate messageTemplate;
        String textMessage = request.getMessage().getText();

        try {
            DialogFlowRequest dialogFlowRequest = new DialogFlowRequest(textMessage);


            DialogFlowResponse dialogFlowResponse = callDialogFlowApi(dialogFlowRequest);
            if (dialogFlowResponse.getResult().getMetadata().getIntentName().equals("thankYou")) {
                return new TextMessageTemplate(request.getSender().getId(), dialogFlowResponse.getResult().getFulfillment().getSpeech());
            } else if (dialogFlowResponse.getResult().getMetadata().getIntentName().equals("help")) {
                return new TextMessageTemplate(request.getSender().getId(), helpMessage);
            } else if (dialogFlowResponse.getResult().getMetadata().getIntentName().equals("dc")) {
                return new TextMessageTemplate(request.getSender().getId(), dialogFlowResponse.getResult().getFulfillment().getSpeech());
            } else {
                //TODO rewrite NPE
                String characterName = dialogFlowResponse.getResult().getParameters().getHeroName();
                MarvelCharacterlResponse marvelCharacterlResponse = callMarvelAPIForChatacter(characterName);

                if (!marvelCharacterlResponse.getData().getResults().isEmpty()) {
                    Long senderPSID = request.getSender().getId();

                    //Saving Responce to database
                    for (CharacterResults results : marvelCharacterlResponse.getData().getResults()) {
                        String character = results.getName();
                        Long characterId = results.getId();
                        userRequestRepository.save(new UserRequestEntity(character, characterId, senderPSID));
                    }
                    messageTemplate = marvelTemplateBuilder.buildGenericTemplateFromMarvelCharacterResponce(request, marvelCharacterlResponse);

                } else {
                    messageTemplate = new TextMessageTemplate(request.getSender().getId(),
                            heroNotFound);
                }
            }

        } catch (NullPointerException e) {
            System.out.println("NPE");
            System.out.println(e.getLocalizedMessage());
            messageTemplate = new TextMessageTemplate(request.getSender().getId(), cantFindHeroName);
        } catch (HttpClientErrorException e) {
            System.out.println("Http exception");
            System.out.println(e.getLocalizedMessage());
            messageTemplate = new TextMessageTemplate(request.getSender().getId(), httpExceptionMessage);
        }

        return messageTemplate;
    }

    private MessageTemplate handleRatingTemplate(Messaging request) {
        Long id = request.getSender().getId();
        return new QuickReplyTemplate(id, getHeroesForQuickReply(ratingReply));
    }

    private MessageTemplate handleComicsTemplate(Messaging request) {
        MessageTemplate template;
        Long limit = userSettingsRepository.getBySenderPSID(request.getSender().getId()).getComicsGivenAtOnce();
        try {

            MarvelComicsResponce marvelComicsResponce = callMarvelAPIForComics(request.getPostback().getPayload(), limit);
            if (!marvelComicsResponce.getData().getResults().isEmpty()) {
                template = marvelTemplateBuilder.buildGenericTemplateFromMarvelComicsResponce(request, marvelComicsResponce);
            } else {
                template = new TextMessageTemplate(request.getSender().getId(), noComicsFound);
            }
        } catch (HttpClientErrorException e) {
            System.out.println("HttpException on comics");
            System.out.println(e.getLocalizedMessage());
            template = new TextMessageTemplate(request.getSender().getId(), httpExceptionMessage);
        }
        return template;
    }

    private MessageTemplate handleMoreComics(Messaging request) {
        MessageTemplate template;

        String characterId = request.getPostback().getPayload().split("/")[0];
        Long offsetLong = Long.parseLong(request.getPostback().getPayload().split("/")[1]);

        try {
            Long limit = userSettingsRepository.getBySenderPSID(request.getSender().getId()).getComicsGivenAtOnce();
            offsetLong += limit;
            String offset = offsetLong.toString();

            MarvelComicsResponce marvelComicsResponce = callMarvelAPIForComics(characterId, limit, offset);
            if (!marvelComicsResponce.getData().getResults().isEmpty()) {
                template = marvelTemplateBuilder.buildGenericTemplateFromMarvelComicsResponce(request, marvelComicsResponce, offset, characterId);
            } else {
                template = new TextMessageTemplate(request.getSender().getId(), noComicsFound);
            }
        } catch (HttpClientErrorException e) {
            System.out.println("HttpException on comics");
            System.out.println(e.getLocalizedMessage());
            template = new TextMessageTemplate(request.getSender().getId(), httpExceptionMessage);
        }
        return template;
    }

    private MessageTemplate handleGreetingQuickReply(Messaging request) {

        MessageTemplate template;
        String characterName = request.getMessage().getText();

        try {
            MarvelCharacterlResponse marvelCharacterlResponse = callMarvelAPIForChatacter(characterName);

            if (!marvelCharacterlResponse.getData().getResults().isEmpty()) {
                Long senderPSID = request.getSender().getId();

                //Saving Responce to database
                for (CharacterResults results : marvelCharacterlResponse.getData().getResults()) {
                    String character = results.getName();
                    Long characterId = results.getId();
                    userRequestRepository.save(new UserRequestEntity(character, characterId, senderPSID));
                }
                template = marvelTemplateBuilder.buildGenericTemplateFromMarvelCharacterResponce(request, marvelCharacterlResponse);

            } else {
                template = new TextMessageTemplate(request.getSender().getId(),
                        heroNotFound);
            }
        } catch (HttpClientErrorException e) {
            System.out.println("HttpException");
            System.out.println(e.getLocalizedMessage());
            template = new TextMessageTemplate(request.getSender().getId(), httpExceptionMessage);
        }

        return template;
    }

    private MessageTemplate handleHelpTemplate(Messaging request) {
        Long recepientId = request.getSender().getId();
        return new TextMessageTemplate(recepientId, helpMessage);
    }

    private MessageTemplate handleTopTemplate(Messaging request) {
        Long recepientId = request.getSender().getId();
        return new QuickReplyTemplate(recepientId, getHeroesForQuickReply(topHeroes));
    }

    private MessageTemplate handleChangeComicsAtOnce(Messaging request) {
        Long senderPSID = request.getSender().getId();
        Long newComicsNumber = Long.parseLong(request.getPostback().getTitle());
        UserSettingsEntity user = userSettingsRepository.getBySenderPSID(senderPSID);
        user.setComicsGivenAtOnce(newComicsNumber);
        userSettingsRepository.save(user);
        return new TextMessageTemplate(senderPSID, settingsChanged);
    }

    private QuickReplyMessage getHeroesForQuickReply(String text) {

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

    private String createHashForCallMarvelApi(String ts) {
        String hash = "hash";
        try {


            MessageDigest md = MessageDigest.getInstance("MD5");

            String toHash = ts + marvelPrivateKey + marvelPublicKey;

            md.update(toHash.getBytes());

            byte byteData[] = md.digest();


            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 Exception");
        }
        return hash;
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

