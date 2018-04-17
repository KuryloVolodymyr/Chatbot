package Bot.Service;

import Bot.DTO.Elements.*;
import Bot.DTO.MarvelDTO.*;
import Bot.DTO.Message.GenericMessage;
import Bot.DTO.RequestDTO.Messaging;
import Bot.DTO.Template.GenericMessageTemplate;
import Bot.DTO.Template.MessageTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;


public class MarvelTemplateBuilder {


    @Value("${marvel.logo}")
    private String marvelLogoUrl;

    @Value("${marvel.imageNotAvailable}")
    private String imageNotAvailable;

    public MessageTemplate buildGenericTemplateFromMarvelCharacterResponse(Messaging request, MarvelCharacterResponse marvelCharacterResponse) {

        List<GenericElement> elements;

        List<CharacterResults> results = marvelCharacterResponse.getData().getResults();

        elements = getGenericElementsForCharacters(results);

        GenericPayload genericPayload = new GenericPayload(elements);
        Attachment attachment = new Attachment(genericPayload);
        GenericMessage genericMessage = new GenericMessage(attachment);

        return new GenericMessageTemplate(request.getSender().getId(), genericMessage);
    }

    public MessageTemplate buildGenericTemplateFromMarvelComicsResponce(Messaging request, MarvelComicsResponse marvelComicsResponse) {

        List<GenericElement> elements;
        String characterId = request.getPostback().getPayload();

        List<ComicsResults> results = marvelComicsResponse.getData().getResults();

        elements = getGenericElementsForComics(results);

        if (marvelComicsResponse.getData().getCount().equals(marvelComicsResponse.getData().getLimit())) {
            List<Button> moreButton = new ArrayList<>();
            moreButton.add(new PostbackButton("More comics", characterId + "/" + 0));
            elements.add(new GenericElement("More comics", "", "http://iconshow.me/media/images/ui/ios7-icons/png/512/more-outline.png", moreButton));
        }
        GenericPayload payload = new GenericPayload(elements);
        Attachment attachment = new Attachment(payload);
        GenericMessage genericMessage = new GenericMessage(attachment);

        return new GenericMessageTemplate(request.getSender().getId(), genericMessage);
    }

    public MessageTemplate buildGenericTemplateFromMarvelComicsResponce(Messaging request, MarvelComicsResponse marvelComicsResponse, String offset, String characterId) {

        List<GenericElement> elements;

        List<ComicsResults> results = marvelComicsResponse.getData().getResults();

        elements = getGenericElementsForComics(results);

        if (marvelComicsResponse.getData().getCount().equals(marvelComicsResponse.getData().getLimit())) {
            List<Button> moreButton = new ArrayList<>();
            moreButton.add(new PostbackButton("More comics", characterId + "/" + offset));
            elements.add(new GenericElement("More comics", "", "http://iconshow.me/media/images/ui/ios7-icons/png/512/more-outline.png", moreButton));
        }
        GenericPayload payload = new GenericPayload(elements);
        Attachment attachment = new Attachment(payload);
        GenericMessage genericMessage = new GenericMessage(attachment);

        return new GenericMessageTemplate(request.getSender().getId(), genericMessage);
    }

    private List<GenericElement> getGenericElementsForCharacters(List<CharacterResults> results) {
        List<GenericElement> elements = new ArrayList<>();
        List<Button> buttons;
        for (CharacterResults result : results) {
            String characherName = result.getName();
            String characherDescription = result.getDescription();

            //Converting Id from Long to String
            String charachterId = result.getId().toString();

            if (characherDescription == null || characherDescription.isEmpty()) {
                characherDescription = "Read more about character";
            }

            //Building link to character image

            String imagePath = result.getThumbnail().getPath();
            String imageExtention = result.getThumbnail().getExtension();
            String imageUrl = imagePath + "." + imageExtention;

            if (imageUrl.equals(imageNotAvailable)) {
                imageUrl = marvelLogoUrl;
            }

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
            buttons = new ArrayList<>();
            if (!wiki.isEmpty()) {
                buttons.add(new LinkButton("Read more", wiki));
            }
            buttons.add(new PostbackButton("Comics", charachterId));
            buttons.add(new PostbackButton("Rate", characherName));
            elements.add(new GenericElement(characherName, characherDescription, imageUrl, buttons));

        }
        return elements;
    }

    private List<GenericElement> getGenericElementsForComics(List<ComicsResults> results) {
        List<GenericElement> elements = new ArrayList<>();
        List<Button> buttons;
        for (ComicsResults result : results) {
            String comicsTitle = result.getTitle();
            String comicsDescription = result.getDecription();

            if (comicsDescription == null || comicsDescription.isEmpty()) {
                comicsDescription = "Read more by clicking button below";
            }

            String imagePath = result.getThumbnail().getPath();
            String imageExtention = result.getThumbnail().getExtension();
            String imageURL = imagePath + "." + imageExtention;

            if (imageURL.equals(imageNotAvailable)) {
                imageURL = marvelLogoUrl;
            }

            String comicsInfo = "";

            List<Urls> urls = result.getUrls();
            for (Urls url : urls) {
                if (url.getType().equals("detail")) {
                    comicsInfo = url.getUrl();
                }
            }
            if (comicsInfo == null || comicsInfo.isEmpty()) {
                elements.add(new GenericElement(comicsTitle, comicsDescription, imageURL));
            } else {
                buttons = new ArrayList<>();
                buttons.add(new LinkButton("Comics info", comicsInfo));
                elements.add(new GenericElement(comicsTitle, comicsDescription, imageURL, buttons));

            }
        }
        return elements;

    }
}
