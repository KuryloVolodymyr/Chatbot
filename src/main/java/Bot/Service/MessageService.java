package Bot.Service;

import Bot.DTO.Elements.*;
import Bot.DTO.MarvelDTO.*;
import Bot.DTO.Message.GenericMessage;
import Bot.DTO.Message.ImageMessage;
import Bot.DTO.Message.QuickReplyMessage;
import Bot.DTO.Recipient;
import Bot.DTO.RequestDTO.RequestHandler;
import Bot.DTO.Template.*;
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

    @Value("${pageAccessToken}")
    private String pageAccessToken;

    @Value("${marvel.privateKey}")
    private String marvelPrivateKey;

    @Value("${marvel.publicKey}")
    private String marvelPublicKey;


    private static final String greeting = "Hi, I am Marvel Bot. I can help you find information about your favorite Marvel Heroes. Just type" +
            " name of Character thay you interested in, or choose one from the heroes below";

    private void callSendAPI(MessageTemplate message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(message, headers);
        String responceFromSendAPI = restTemplate.postForObject("https://graph.facebook.com/v2.6/me/messages?access_token={token}",
                entity, String.class, pageAccessToken);
        System.out.println(responceFromSendAPI);

    }

    private MarvelCharacterlResponse callMarvelAPIForChatacter(String characterName) {
        String ts = new Timestamp(System.currentTimeMillis()).toString();
        String limit = "5";

        String hash = createHashForCallMarvelApi(ts);

        return restTemplate.getForObject("https://gateway.marvel.com:443/v1/public/characters?name={name}&limit={limit}&ts={ts}&apikey={key}&hash={hash}",
                MarvelCharacterlResponse.class, characterName, limit, ts, marvelPublicKey, hash);

    }

//    private MarvelComicsResponce callMarvelAPIForComics(String characterId) {
//        String ts = new Timestamp(System.currentTimeMillis()).toString();
//        String limit = "5";
//
//        String hash = createHashForCallMarvelApi(ts);
//
//        return restTemplate.getForObject("https://gateway.marvel.com:443/v1/public/characters/{characterId}/comics?&limit={limit}&ts={ts}&apikey={key}&hash={hash}",
//                MarvelComicsResponce.class, characterId, limit, ts, marvelPublicKey, hash);
//
//    }

    private MessageTemplate handleImageMessage(RequestHandler request) {

        Recipient recipient = new Recipient(request.getEntry().get(0).getMessaging().get(0).getSender().getId());
        String url = request.getEntry().get(0).getMessaging().get(0).getMessage().getAttachments().get(0).getPayload().getUrl();

        ImagePayload payload = new ImagePayload(url);
        ImageAttachment imageAttachment = new ImageAttachment(payload);
        ImageMessage imageMessage = new ImageMessage(imageAttachment);

        return new ImageTemplate(recipient, imageMessage);
    }

    private MessageTemplate handleGreeting(RequestHandler request) {
        return new QuickReplyTemplate(request.getEntry().get(0).getMessaging().get(0).getSender().getId(), getHeroesForQuickReply(greeting));
    }

    private MessageTemplate handleTextMessage(RequestHandler request) {
        MessageTemplate messageTemplate;
        String characterName = request.getEntry().get(0).getMessaging().get(0).getMessage().getText();
        MarvelCharacterlResponse marvelCharacterlResponse = callMarvelAPIForChatacter(characterName);

        if (!marvelCharacterlResponse.getData().getResults().isEmpty()) {
            messageTemplate = buildGenericTemplateFromMarvelCharacterResponce(request, marvelCharacterlResponse);
        } else {
            messageTemplate = new TextMessageTemplate(request.getEntry().get(0).getMessaging().get(0).getSender().getId(),
                    "Sorry, I couldn`t find this character, mayby you`ve typed his name wrong. Try again, please");
        }
        return messageTemplate;
    }

    public void processMessage(RequestHandler request) {

        long id = request.getEntry().get(0).getMessaging().get(0).getSender().getId();
        MessageTemplate template = new TextMessageTemplate(id, "Sorry, something went wrong (");


        if (!isText(request)) {
            System.out.println("Not text");
            if (isImage(request)) {
                System.out.println("Is image");
                template = handleImageMessage(request);
            } else if (isStart(request)) {
                System.out.println("Is start");
                template = handleGreeting(request);
            }
//            else if (isComicsList(request))
//            {
//                MarvelComicsResponce marvelComicsResponce = callMarvelAPIForComics(request.getEntry().get(0).getMessaging().get(0).getPostback().getPayload());
//                template = buildGenericTemplateFromMarvelComicsResponce(request ,marvelComicsResponce);
//                System.out.println("template build");
//            }
        } else {
            System.out.println("Is text");
            template = handleTextMessage(request);
        }
        System.out.println("calling send api");
        callSendAPI(template);
    }

    private boolean isStart(RequestHandler request) {
        if (request.getEntry().get(0).getMessaging().get(0).getPostback() == null) {
            return false;
        } else {
            if (request.getEntry().get(0).getMessaging().get(0).getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getEntry().get(0).getMessaging().get(0).getPostback().getPayload().equals("Start");
            }
        }
    }

    private boolean isComicsList(RequestHandler request) {
        if (request.getEntry().get(0).getMessaging().get(0).getPostback() == null) {
            return false;
        } else {
            if (request.getEntry().get(0).getMessaging().get(0).getPostback().getPayload() == null) {
                return false;
            } else {
               return request.getEntry().get(0).getMessaging().get(0).getPostback().getTitle().equals("Comics");
            }
        }
    }

    private boolean isImage(RequestHandler request) {
        if (request.getEntry().get(0).getMessaging().get(0).getMessage() == null) {
            return false;
        } else {
            if (request.getEntry().get(0).getMessaging().get(0).getMessage().getAttachments() == null) {
                return false;
            } else {
                if (request.getEntry().get(0).getMessaging().get(0).getMessage().getAttachments().get(0).getType().equals("image")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private boolean isText(RequestHandler request) {
        if (request.getEntry().get(0).getMessaging().get(0).getMessage() == null) {
            return false;
        } else {
            if (request.getEntry().get(0).getMessaging().get(0).getMessage().getText() == null) {
                return false;
            } else
                return true;
        }
    }

    private QuickReplyMessage getHeroesForQuickReply(String text) {
        List<QuickReply> quickReplies = new ArrayList<>();
        quickReplies.add(new QuickReply("text", "Ant-Man", "Ant-Man"));
        quickReplies.add(new QuickReply("text", "Iron-Man", "Iron-Man"));
        quickReplies.add(new QuickReply("text", "Hulk", "Hulk"));
        quickReplies.add(new QuickReply("text", "Spider-Man", "Spider-Man"));
        quickReplies.add(new QuickReply("text", "Sandman", "Sandman"));
        quickReplies.add(new QuickReply("text", "Captain America", "Captain America"));
        quickReplies.add(new QuickReply("text", "Thor", "Thor"));
        quickReplies.add(new QuickReply("text", "Vision", "Vision"));
        quickReplies.add(new QuickReply("text", "Starlord", "Starlord"));
        quickReplies.add(new QuickReply("text", "Doctor Strange", "Doctor Strange"));
        return new QuickReplyMessage(text, quickReplies);
    }

    private MessageTemplate buildGenericTemplateFromMarvelCharacterResponce(RequestHandler request, MarvelCharacterlResponse marvelCharacterlResponse) {

        GenericElement genericElement;
        List<GenericElement> elements = new ArrayList<>();
        List<Button> buttons = new ArrayList<>();

        List<CharacterResults> results = marvelCharacterlResponse.getData().getResults();

        for (CharacterResults result : results) {
            String characherName = result.getName();
            String characherDescription = result.getDescription();

            //Converting Id from Long to String
            String charachterId = result.getId().toString();

            if (characherDescription.isEmpty()) {
                characherDescription = "Read about character on wiki";
            }

            //Building link to character image
            String imagePath = result.getThumbnail().getPath();
            String imageExtention = result.getThumbnail().getExtension();
            String imageUrl = imagePath + "." + imageExtention;

            String wiki = "";

            // Search for link to Marvel Wiki page of character
            List<Urls> urls = result.getUrls();
            for (Urls url : urls) {
                if (url.getType().equals("wiki")) {
                    wiki = url.getUrl();
                }
            }

            //If there is link to wiki building generic template with link button,
            //if there is no link to wiki building generic template without buttons
            if (!wiki.isEmpty()) {
                buttons.add(new LinkButton("Read more on Wiki", wiki));
            }
            buttons.add(new PostbackButton("Comics", charachterId));
            genericElement = new GenericElement(characherName, characherDescription, imageUrl, buttons);


            elements.add(genericElement);

        }
        GenericPayload genericPayload = new GenericPayload(elements);
        Attachment attachment = new Attachment(genericPayload);
        GenericMessage genericMessage = new GenericMessage(attachment);

        return new GenericMessageTemplate(request.getEntry().get(0).getMessaging().get(0).getSender().getId(), genericMessage);
    }


    //TODO
//    private MessageTemplate buildGenericTemplateFromMarvelComicsResponce(RequestHandler request, MarvelComicsResponce marvelComicsResponse) {
//
//        GenericElement genericElement;
//        List<GenericElement> elements = new ArrayList<>();
//        List<Button> buttons = new ArrayList<>();
//
//        List<ComicsResults> results = marvelComicsResponse.getData().getResults();
//
//
//
//        for (ComicsResults result : results) {
//            String comicsTitle = result.getTitle();
//            String comicsDescription = result.getDecription();
//
//            System.out.println(comicsTitle);
//            System.out.println(comicsDescription);
//            if (comicsDescription == null)
//            {
//                comicsDescription = "Click Info to Read About Comics";
//            }
//            else{
//                if(result.getDecription().isEmpty()){
//                    comicsDescription = "Click Info to Read About Comics";
//                }
//            }
//
//            String imagePath = result.getThumbnail().getPath();
//            String imageExtension = result.getThumbnail().getExtension();
//
//            String imageUrl = imagePath+"."+imageExtension;
//
//            String comicsInfo = "";
//
//            List<Urls> urls = result.getUrls();
//            for (Urls url : urls) {
//                if (url.getType().equals("detail")) {
//                    comicsInfo = url.getUrl();
//                }
//            }
//
//            if (!comicsInfo.isEmpty()) {
//                buttons.add(new LinkButton("Read more info on comics", comicsInfo));
//                genericElement = new GenericElement(comicsTitle, comicsDescription, imageUrl, buttons);
//            } else {
//                genericElement = new GenericElement(comicsTitle, comicsDescription, imageUrl);
//            }
//            elements.add(genericElement);
//
//        }
//        GenericPayload genericPayload = new GenericPayload(elements);
//        Attachment attachment = new Attachment(genericPayload);
//
//        GenericMessage genericMessage = new GenericMessage(attachment);
//
//        System.out.println("building template");
//        //Throws NPE ???
//
//        return new GenericMessageTemplate(request.getEntry().get(0).getMessaging().get(0).getSender().getId(), genericMessage);
//    }

    private String createHashForCallMarvelApi(String ts){
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
