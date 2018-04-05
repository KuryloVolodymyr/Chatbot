package Bot.Service;

import Bot.DTO.DialogFlowDTO.DialogFlowRequest.DialogFlowRequest;
import Bot.DTO.DialogFlowDTO.DialogFlowResponse.DialogFlowResponse;
import Bot.DTO.MarvelDTO.CharacterResults;
import Bot.DTO.MarvelDTO.MarvelCharacterResponse;
import Bot.DTO.MarvelDTO.MarvelComicsResponse;
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

public class MessageHandler {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ApiCaller apiCaller;

    @Autowired
    private MarvelTemplateBuilder marvelTemplateBuilder;

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private UserSettingsRepository userSettingsRepository;

    @Autowired
    private MessageTypeDetector messageTypeDetector;


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

    public MessageTemplate handleNonTextMessage(Messaging request) {

        long id = request.getSender().getId();
        MessageTemplate template = new TextMessageTemplate(id, noTemplateInitialized);
        if (messageTypeDetector.isImage(request)) {
            template = handleImageMessage(request);
        } else if (messageTypeDetector.isStart(request)) {
            template = handleGreeting(request);
        } else if (messageTypeDetector.isHelp(request)) {
            template = handleHelpTemplate(request);
        } else if (messageTypeDetector.isTop(request)) {
            template = handleTopTemplate(request);
        } else if (messageTypeDetector.isChangeComicsAmound(request)) {
            template = handleChangeComicsAtOnce(request);
        } else if (messageTypeDetector.isGetComics(request)) {
            template = handleComicsTemplate(request);
        } else if (messageTypeDetector.isMoreComics(request)) {
            template = handleMoreComics(request);
        } else if (messageTypeDetector.isRate(request)) {
            template = handleRatingTemplate(request);

        }
        return template;
    }

    public MessageTemplate handleMessageWithText(Messaging request) {

        long id = request.getSender().getId();
        MessageTemplate template = new TextMessageTemplate(id, noTemplateInitialized);
        if (messageTypeDetector.isQuickReply(request)) {
            if (messageTypeDetector.isHeroQuickReply(request)) {
                template = handleGreetingQuickReply(request);
            } else if (messageTypeDetector.isRatingQuickReply(request)) {
                messageService.rateHero(request);
                template = handleRatingSuccessfullTemplate(request);
            }
        } else {
            template = handleTextMessage(request);
        }
        return template;
    }

    private MessageTemplate handleImageMessage(Messaging request) {

        return new TextMessageTemplate(request.getSender().getId(), imageHandleMessage);
    }

    private MessageTemplate handleGreeting(Messaging request) {
        Long senderPSID = request.getSender().getId();
        if (userSettingsRepository.getBySenderPSID(senderPSID) == null) {
            userSettingsRepository.save(new UserSettingsEntity(senderPSID));
        }
        return new QuickReplyTemplate(senderPSID, messageService.getHeroesForQuickReply(greeting));
    }

    private MessageTemplate handleTextMessage(Messaging request) {
        MessageTemplate messageTemplate;
        String textMessage = request.getMessage().getText();


        DialogFlowRequest dialogFlowRequest = new DialogFlowRequest(textMessage);

        DialogFlowResponse dialogFlowResponse = apiCaller.callDialogFlowApi(dialogFlowRequest);

        if (dialogFlowResponse.getResult().getMetadata().getIntentName() == null) {
            messageTemplate = new TextMessageTemplate(request.getSender().getId(), cantFindHeroName);
        } else {
            messageTemplate = handleDialogflowResponce(dialogFlowResponse, request);
        }


        return messageTemplate;
    }

    private MessageTemplate handleRatingTemplate(Messaging request) {
        long id = request.getSender().getId();
        return new QuickReplyTemplate(id, messageService.getRatingQuickReply(request.getPostback().getPayload()));
    }

    private MessageTemplate handleRatingSuccessfullTemplate(Messaging request) {
        Long id = request.getSender().getId();
        return new QuickReplyTemplate(id, messageService.getHeroesForQuickReply(ratingReply));
    }

    private MessageTemplate handleComicsTemplate(Messaging request) {

        MessageTemplate template;
        Long limit = userSettingsRepository.getBySenderPSID(request.getSender().getId()).getComicsGivenAtOnce();
        MarvelComicsResponse marvelComicsResponse = apiCaller.callMarvelAPIForComics(request.getPostback().getPayload(), limit);
        if (!marvelComicsResponse.getData().getResults().isEmpty()) {
            template = marvelTemplateBuilder.buildGenericTemplateFromMarvelComicsResponce(request, marvelComicsResponse);
        } else {
            template = new TextMessageTemplate(request.getSender().getId(), noComicsFound);
        }

        return template;
    }

    private MessageTemplate handleMoreComics(Messaging request) {
        MessageTemplate template;

        String characterId = request.getPostback().getPayload().split("/")[0];
        Long offsetLong = Long.parseLong(request.getPostback().getPayload().split("/")[1]);

        Long limit = userSettingsRepository.getBySenderPSID(request.getSender().getId()).getComicsGivenAtOnce();
        offsetLong += limit;
        String offset = offsetLong.toString();

        MarvelComicsResponse marvelComicsResponse = apiCaller.callMarvelAPIForComics(characterId, limit, offset);
        if (!marvelComicsResponse.getData().getResults().isEmpty()) {
            template = marvelTemplateBuilder.buildGenericTemplateFromMarvelComicsResponce(request, marvelComicsResponse, offset, characterId);
        } else {
            template = new TextMessageTemplate(request.getSender().getId(), noComicsFound);
        }

        return template;
    }

    private MessageTemplate handleGreetingQuickReply(Messaging request) {

        MessageTemplate template;
        String characterName = request.getMessage().getText();

        MarvelCharacterResponse marvelCharacterResponse = apiCaller.callMarvelAPIForCharacter(characterName);

        template = getMarvelCharacter(request, marvelCharacterResponse);

        return template;
    }

    private MessageTemplate handleHelpTemplate(Messaging request) {
        Long recepientId = request.getSender().getId();
        return new TextMessageTemplate(recepientId, helpMessage);
    }

    private MessageTemplate handleTopTemplate(Messaging request) {
        Long recepientId = request.getSender().getId();
        return new QuickReplyTemplate(recepientId, messageService.getHeroesForQuickReply(topHeroes));
    }

    private MessageTemplate handleChangeComicsAtOnce(Messaging request) {
        Long senderPSID = request.getSender().getId();
        Long newComicsNumber = Long.parseLong(request.getPostback().getTitle());
        UserSettingsEntity user = userSettingsRepository.getBySenderPSID(senderPSID);
        user.setComicsGivenAtOnce(newComicsNumber);
        userSettingsRepository.save(user);
        return new TextMessageTemplate(senderPSID, settingsChanged);
    }

    private MessageTemplate handleDialogflowResponce(DialogFlowResponse dialogFlowResponse, Messaging request) {
        //Checking if type of intent is one than needs special haldeling
        // if not, handling it in getTemplateForCharacter method
        MessageTemplate messageTemplate;
        String intentType = dialogFlowResponse.getResult().getMetadata().getIntentName();
        switch (intentType) {
            case "thankYou":
                messageTemplate = new TextMessageTemplate(request.getSender().getId(), dialogFlowResponse.getResult().getFulfillment().getSpeech());
                break;
            case "help":
                messageTemplate = new TextMessageTemplate(request.getSender().getId(), helpMessage);
                break;
            case "dc":
                messageTemplate = new TextMessageTemplate(request.getSender().getId(), dialogFlowResponse.getResult().getFulfillment().getSpeech());
                break;
            default:
                messageTemplate = getTemplateForCharacter(request, dialogFlowResponse);
                break;
        }
        return messageTemplate;
    }

    private MessageTemplate getTemplateForCharacter(Messaging request, DialogFlowResponse dialogFlowResponse) {
        // if parameters field is empty, than we can`t find hero name
        // otherwise calling marvel api with character name found in parameters
        MessageTemplate messageTemplate;
        if (dialogFlowResponse.getResult().getParameters() == null) {
            messageTemplate = new TextMessageTemplate(request.getSender().getId(), cantFindHeroName);
        } else {
            String characterName = dialogFlowResponse.getResult().getParameters().getHeroName();
            MarvelCharacterResponse marvelCharacterResponse = apiCaller.callMarvelAPIForCharacter(characterName);

            messageTemplate = getMarvelCharacter(request, marvelCharacterResponse);
        }
        return messageTemplate;
    }

    private MessageTemplate getMarvelCharacter(Messaging request, MarvelCharacterResponse marvelCharacterResponse) {
        //if marvel API returned some character info we save it to database and pass it to MarvelCharacterResponceBuilder
        //otherwise sending "heroNotFound" message
        MessageTemplate template;
        if (!marvelCharacterResponse.getData().getResults().isEmpty()) {
            Long senderPSID = request.getSender().getId();

            //Saving Responce to database
            for (CharacterResults results : marvelCharacterResponse.getData().getResults()) {
                String character = results.getName();
                Long characterId = results.getId();
                userRequestRepository.save(new UserRequestEntity(character, characterId, senderPSID));
            }
            template = marvelTemplateBuilder.buildGenericTemplateFromMarvelCharacterResponse(request, marvelCharacterResponse);

        } else {
            template = new TextMessageTemplate(request.getSender().getId(),
                    heroNotFound);
        }
        return template;
    }
}
