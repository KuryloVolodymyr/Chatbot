package Bot.Service;

import Bot.DTO.Elements.*;
import Bot.DTO.MarvelDTO.*;
import Bot.DTO.Message.GenericMessage;
import Bot.DTO.Message.ImageMessage;
import Bot.DTO.Message.QuickReplyMessage;
import Bot.DTO.Recipient;
import Bot.DTO.RequestDTO.Messaging;
import Bot.DTO.RequestDTO.RequestHandler;
import Bot.DTO.Template.*;
import Bot.Domain.HeroesRatingEntity;
import Bot.Domain.UserRequestEntity;
import Bot.Repository.HeroesRatingRepository;
import Bot.Repository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class MessageService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private HeroesRatingRepository heroesRatingRepository;

    @Autowired
    private MarvelTemplateBuilder marvelTemplateBuilder;

    @Value("${pageAccessToken}")
    private String pageAccessToken;

    @Value("${marvel.privateKey}")
    private String marvelPrivateKey;

    @Value("${marvel.publicKey}")
    private String marvelPublicKey;

    @Value("${responce.greeting}")
    private String greeting;

    @Value("${responce.herNotFound}")
    private String heroNotFound;

    @Value("${responce.noTemplateInitialized}")
    private String noTemplateInitialized;

    private void callSendAPI(MessageTemplate message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(message, headers);
        String responceFromSendAPI = restTemplate.postForObject("https://graph.facebook.com/v2.6/me/messages?access_token={token}",
                entity, String.class, pageAccessToken);
        System.out.println("OK");

    }

    private MarvelCharacterlResponse callMarvelAPIForChatacter(String characterName) {
        String ts = new Timestamp(System.currentTimeMillis()).toString();
        String limit = "5";

        String hash = createHashForCallMarvelApi(ts);

        return restTemplate.getForObject("https://gateway.marvel.com:443/v1/public/characters?name={name}&limit={limit}&ts={ts}&apikey={key}&hash={hash}",
                MarvelCharacterlResponse.class, characterName, limit, ts, marvelPublicKey, hash);

    }

    private MarvelComicsResponce callMarvelAPIForComics(String characterId) {
        String ts = new Timestamp(System.currentTimeMillis()).toString();
        String limit = "5";

        String hash = createHashForCallMarvelApi(ts);

        return restTemplate.getForObject("https://gateway.marvel.com:443/v1/public/characters/{characterId}/comics?&limit={limit}&ts={ts}&apikey={key}&hash={hash}",
                MarvelComicsResponce.class, characterId, limit, ts, marvelPublicKey, hash);

    }

    private MessageTemplate handleImageMessage(Messaging request) {

        Recipient recipient = new Recipient(request.getSender().getId());
        String url = request.getMessage().getAttachments().get(0).getPayload().getUrl();

        ImagePayload payload = new ImagePayload(url);
        ImageAttachment imageAttachment = new ImageAttachment(payload);
        ImageMessage imageMessage = new ImageMessage(imageAttachment);

        return new ImageTemplate(recipient, imageMessage);
    }

    private MessageTemplate handleGreeting(Messaging request) {
        return new QuickReplyTemplate(request.getSender().getId(), getHeroesForQuickReply(greeting));
    }

    private MessageTemplate handleTextMessage(Messaging request) {
        MessageTemplate messageTemplate;
        String characterName = request.getMessage().getText();
        MarvelCharacterlResponse marvelCharacterlResponse = callMarvelAPIForChatacter(characterName);

        if (!marvelCharacterlResponse.getData().getResults().isEmpty()) {
            messageTemplate = marvelTemplateBuilder.buildGenericTemplateFromMarvelCharacterResponce(request, marvelCharacterlResponse);
            String character = marvelCharacterlResponse.getData().getResults().get(0).getName();
            Long characterId = marvelCharacterlResponse.getData().getResults().get(0).getId();
            Long senderPSID = request.getSender().getId();
            userRequestRepository.save(new UserRequestEntity(character, characterId, senderPSID));
        } else {
            messageTemplate = new TextMessageTemplate(request.getSender().getId(),
                    heroNotFound);
        }
        return messageTemplate;
    }

    private MessageTemplate handleRatingTemplate(Messaging request) {
        Long id = request.getSender().getId();
        String reply = "You`ve successfully rated the Hero, lets keep going";
        return new TextMessageTemplate(id, reply);
    }

    public void processMessage(Messaging request) {

        long id = request.getSender().getId();
        MessageTemplate template = new TextMessageTemplate(id, noTemplateInitialized);


        if (!isText(request)) {
            if (isImage(request)) {
                template = handleImageMessage(request);
            } else if (isStart(request)) {
                template = handleGreeting(request);
            } else if (isGetComics(request)) {
                MarvelComicsResponce marvelComicsResponce = callMarvelAPIForComics(request.getPostback().getPayload());
                template = marvelTemplateBuilder.buildGenericTemplateFromMarvelComicsResponce(request, marvelComicsResponce);
            } else if (isRate(request)) {
                template = new QuickReplyTemplate(id, getRatingQuickReply(request.getPostback().getPayload()));
            }
        } else {
            if (isQuickReply(request)) {
                if (isHeroQuickReply(request)) {
                    template = handleTextMessage(request);
                } else if (isRatingQuickReply(request)) {

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
                    template = handleRatingTemplate(request);
                } else {
                    System.out.println("Quick reply not trigered");
                }
            } else {
                template = handleTextMessage(request);
            }
        }
        callSendAPI(template);
    }

    private boolean isStart(Messaging request) {
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getPayload().equals("Start");
            }
        }
    }

    private boolean isGetComics(Messaging request) {
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getTitle().equals("Comics");
            }
        }
    }

    private boolean isImage(Messaging request) {
        if (request.getMessage() == null) {
            return false;
        } else {
            if (request.getMessage().getAttachments() == null) {
                return false;
            } else {
                if (request.getMessage().getAttachments().get(0).getType().equals("image")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private boolean isText(Messaging request) {
        if (request.getMessage() == null) {
            return false;
        } else {
            if (request.getMessage().getText() == null) {
                return false;
            } else
                return true;
        }
    }

    private boolean isRate(Messaging request) {
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getTitle().equals("Rate");
            }
        }
    }

    private boolean isQuickReply(Messaging request) {
        if (request.getMessage() == null) {
            return false;
        } else {
            if (request.getMessage().getQuickReply() == null) {
                return false;
            } else
                return true;
        }
    }

    private boolean isHeroQuickReply(Messaging request) {
        return request.getMessage().getQuickReply().getPayload().equals("hero");
    }

    private boolean isRatingQuickReply(Messaging request) {
        String reply = request.getMessage().getText();
        return reply.equals("\uD83D\uDC4D") || reply.equals("\uD83D\uDC4E");
    }

    private QuickReplyMessage getHeroesForQuickReply(String text) {
        List<QuickReply> quickReplies = new ArrayList<>();
        quickReplies.add(new QuickReply("text", "Ant-Man", "hero"));
        quickReplies.add(new QuickReply("text", "Iron Man", "hero"));
        quickReplies.add(new QuickReply("text", "Hulk", "hero"));
        quickReplies.add(new QuickReply("text", "Spider-Man", "hero"));
        quickReplies.add(new QuickReply("text", "Sandman", "hero"));
        quickReplies.add(new QuickReply("text", "Captain America", "hero"));
        quickReplies.add(new QuickReply("text", "Thor", "hero"));
        quickReplies.add(new QuickReply("text", "Vision", "hero"));
        quickReplies.add(new QuickReply("text", "Starlord", "hero"));
        quickReplies.add(new QuickReply("text", "Doctor Strange", "hero"));
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

    private MessageTemplate genericTest(RequestHandler request) {
        List<Button> buttons = new ArrayList<>();
        buttons.add(new PostbackButton("yes", "YES"));
        buttons.add(new PostbackButton("no", "NO"));

        List<GenericElement> elements = new ArrayList<>();
        elements.add(new GenericElement("Title 1", "Sub 1", "http://i.annihil.us/u/prod/marvel/i/mg/3/50/537ba56d31087.jpg"));
        elements.add(new GenericElement("Title 2", "Sub 2", "http://i.annihil.us/u/prod/marvel/i/mg/3/50/537ba56d31087.jpg"));

        GenericPayload genericPayload = new GenericPayload(elements);

        Attachment attachment = new Attachment(genericPayload);
        GenericMessage genericMessage = new GenericMessage(attachment);
        return new GenericMessageTemplate(request.getEntry().get(0).getMessaging().get(0).getSender().getId(), genericMessage);
    }
}
