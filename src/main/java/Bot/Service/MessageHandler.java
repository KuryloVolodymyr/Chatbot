package Bot.Service;

import Bot.DTO.DialogFlowDTO.DialogFlowRequest.DialogFlowRequest;
import Bot.DTO.DialogFlowDTO.DialogFlowResponse.DialogFlowResponse;
import Bot.DTO.MarvelDTO.CharacterResults;
import Bot.DTO.MarvelDTO.MarvelCharacterlResponse;
import Bot.DTO.MarvelDTO.MarvelComicsResponce;
import Bot.DTO.RequestDTO.Messaging;
import Bot.DTO.Template.MessageTemplate;
import Bot.DTO.Template.QuickReplyTemplate;
import Bot.DTO.Template.TextMessageTemplate;
import Bot.Domain.UserRequestEntity;
import Bot.Domain.UserSettingsEntity;
import Bot.Repository.UserRequestRepository;
import Bot.Repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpClientErrorException;

public class MessageHandler {

    @Autowired
    MessageService messageService;

    @Autowired
    UserSettingsRepository userSettingsRepository;

    @Autowired
    ApiCaller apiCaller;

    @Autowired
    MarvelTemplateBuilder marvelTemplateBuilder;

    @Autowired
    private UserRequestRepository userRequestRepository;

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

    public MessageTemplate handleImageMessage(Messaging request) {

//        String url = request.getMessage().getAttachments().get(0).getPayload().getUrl();
//
//        ImagePayload payload = new ImagePayload(url);
//        ImageAttachment imageAttachment = new ImageAttachment(payload);
//        ImageMessage imageMessage = new ImageMessage(imageAttachment);
//
//        return new ImageTemplate(recipient, imageMessage);

        return new TextMessageTemplate(request.getSender().getId(), imageHandleMessage);
    }

    public MessageTemplate handleGreeting(Messaging request) {
        Long senderPSID = request.getSender().getId();
        if (userSettingsRepository.getBySenderPSID(senderPSID) == null) {
            userSettingsRepository.save(new UserSettingsEntity(senderPSID));
        }
        return new QuickReplyTemplate(senderPSID, messageService.getHeroesForQuickReply(greeting));
    }

    public MessageTemplate handleTextMessage(Messaging request) {
        MessageTemplate messageTemplate;
        String textMessage = request.getMessage().getText();

        try {
            DialogFlowRequest dialogFlowRequest = new DialogFlowRequest(textMessage);

            DialogFlowResponse dialogFlowResponse = apiCaller.callDialogFlowApi(dialogFlowRequest);


            System.out.println(dialogFlowResponse.getResult().getMetadata().isEmpty());

            if (dialogFlowResponse.getResult().getMetadata().getIntentName() == null) {
                messageTemplate = new TextMessageTemplate(request.getSender().getId(), cantFindHeroName);
            } else {
                if (dialogFlowResponse.getResult().getMetadata().getIntentName().equals("thankYou")) {
                    return new TextMessageTemplate(request.getSender().getId(), dialogFlowResponse.getResult().getFulfillment().getSpeech());
                } else if (dialogFlowResponse.getResult().getMetadata().getIntentName().equals("help")) {
                    return new TextMessageTemplate(request.getSender().getId(), helpMessage);
                } else if (dialogFlowResponse.getResult().getMetadata().getIntentName().equals("dc")) {
                    return new TextMessageTemplate(request.getSender().getId(), dialogFlowResponse.getResult().getFulfillment().getSpeech());
                } else {
                    if (dialogFlowResponse.getResult().getParameters() == null) {
                        System.out.println("Can`t find hero");
                        messageTemplate = new TextMessageTemplate(request.getSender().getId(), cantFindHeroName);
                    } else {
                        String characterName = dialogFlowResponse.getResult().getParameters().getHeroName();
                        MarvelCharacterlResponse marvelCharacterlResponse = apiCaller.callMarvelAPIForChatacter(characterName);

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
                }
            }


        } catch (HttpClientErrorException e) {
            System.out.println("Http exception");
            System.out.println(e.getLocalizedMessage());
            messageTemplate = new TextMessageTemplate(request.getSender().getId(), httpExceptionMessage);
        }

        return messageTemplate;
    }

    public MessageTemplate handleRatingTemplate(Messaging request) {
        Long id = request.getSender().getId();
        return new QuickReplyTemplate(id, messageService.getHeroesForQuickReply(ratingReply));
    }

    public MessageTemplate handleComicsTemplate(Messaging request) {
        MessageTemplate template;
        Long limit = userSettingsRepository.getBySenderPSID(request.getSender().getId()).getComicsGivenAtOnce();
        try {

            MarvelComicsResponce marvelComicsResponce = apiCaller.callMarvelAPIForComics(request.getPostback().getPayload(), limit);
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

    public MessageTemplate handleMoreComics(Messaging request) {
        MessageTemplate template;

        String characterId = request.getPostback().getPayload().split("/")[0];
        Long offsetLong = Long.parseLong(request.getPostback().getPayload().split("/")[1]);

        try {
            Long limit = userSettingsRepository.getBySenderPSID(request.getSender().getId()).getComicsGivenAtOnce();
            offsetLong += limit;
            String offset = offsetLong.toString();

            MarvelComicsResponce marvelComicsResponce = apiCaller.callMarvelAPIForComics(characterId, limit, offset);
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

    public MessageTemplate handleGreetingQuickReply(Messaging request) {

        MessageTemplate template;
        String characterName = request.getMessage().getText();

        try {
            MarvelCharacterlResponse marvelCharacterlResponse = apiCaller.callMarvelAPIForChatacter(characterName);

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

    public MessageTemplate handleHelpTemplate(Messaging request) {
        Long recepientId = request.getSender().getId();
        return new TextMessageTemplate(recepientId, helpMessage);
    }

    public MessageTemplate handleTopTemplate(Messaging request) {
        Long recepientId = request.getSender().getId();
        return new QuickReplyTemplate(recepientId, messageService.getHeroesForQuickReply(topHeroes));
    }

    public MessageTemplate handleChangeComicsAtOnce(Messaging request) {
        Long senderPSID = request.getSender().getId();
        Long newComicsNumber = Long.parseLong(request.getPostback().getTitle());
        UserSettingsEntity user = userSettingsRepository.getBySenderPSID(senderPSID);
        user.setComicsGivenAtOnce(newComicsNumber);
        userSettingsRepository.save(user);
        return new TextMessageTemplate(senderPSID, settingsChanged);
    }
}
