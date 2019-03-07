package bot.service;

import bot.dto.Broadcast.*;
import bot.dto.DialogFlowDTO.DialogFlowRequest.DialogFlowRequest;
import bot.dto.DialogFlowDTO.DialogFlowResponse.DialogFlowResponse;
import bot.dto.MarvelDTO.MarvelCharacterResponse;
import bot.dto.MarvelDTO.MarvelComicsResponse;
import bot.dto.Template.MessageTemplate;
import bot.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Arrays;

@Component("apiCaller")
@Primary
public class ApiCaller {

    private static final String numberOfCharactersPerRequest = "5";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.marvel.charactersByName}")
    private String marvelCharactersByNameURL;

    @Value("${url.marvel.charactersNameStartsWith}")
    private String marvelCharactersStartsWith;

    @Value("${url.marver.comics}")
    private String marvelComics;

    @Value("${url.marvel.comicsWithOffset}")
    private String marvelComicsWithOffset;

    @Value("${url.messenger.sendAPIURL}")
    protected String sendAPIURL;

    @Value("${url.messenger.graphAPIURL}")
    private String graphAPIURL;

    @Value("${url.dialogflow}")
    private String dialogFlowUrl;

    @Value("${pageAccessToken}")
    private String pageAccessToken;

    @Value("${marvel.privateKey}")
    private String marvelPrivateKey;

    @Value("${marvel.publicKey}")
    private String marvelPublicKey;

    @Value("${dialogFlow.clientAccessToken}")
    private String dialogFlowClientToken;


    public void callSendAPI(MessageTemplate message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(message, headers);
        System.out.println(sendAPIURL);
        try {
            restTemplate.postForObject(sendAPIURL, entity, String.class, pageAccessToken);
        } catch (HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
        }

    }

    public void broadcastAPI(String text) {
        System.out.println("text " + text);
        DynamicText dynamicText = new DynamicText(text + " {{first_name}}", "Hello");
        Message message = new Message(dynamicText);
        BroadcastMessages broadcastMessages = new BroadcastMessages(Arrays.asList(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(broadcastMessages, headers);

        try {
            MessageIdObject id = restTemplate.postForObject("https://graph.facebook.com/v2.11/me/message_creatives?access_token={token}", entity, MessageIdObject.class, pageAccessToken);
            System.out.println("id = " + id.getMessage_creative_id());

            Broadcast broadcast = new Broadcast(id.getMessage_creative_id());

            HttpHeaders otherHeaders = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> newEntity = new HttpEntity<>(broadcast, otherHeaders);

            String response = restTemplate.postForObject("https://graph.facebook.com/v2.11/me/broadcast_messages?access_token={token}", newEntity, String.class, pageAccessToken);
            System.out.println("response " + response);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
        }
    }

    public UserProfile callGraphApi(Long recepientId) {
        return restTemplate.getForObject(graphAPIURL, UserProfile.class, recepientId, pageAccessToken);
    }

    public MarvelCharacterResponse callMarvelAPIForCharacter(String characterName) {

        String limit = numberOfCharactersPerRequest;
        MarvelCharacterResponse marvelCharacterResponse;
        String ts = new Timestamp(System.currentTimeMillis()).toString();

        String hash = createHashForCallMarvelApi(ts);

        marvelCharacterResponse = restTemplate.getForObject(marvelCharactersByNameURL, MarvelCharacterResponse.class,
                characterName, limit, ts, marvelPublicKey, hash);

        if (marvelCharacterResponse.getData().getResults().isEmpty()) {
            marvelCharacterResponse = callMarvelAPIForChatacterNameStartsWith(characterName, limit);
        }
        return marvelCharacterResponse;
    }

    public MarvelComicsResponse callMarvelAPIForComics(String characterId, Long limit) {
        String ts = new Timestamp(System.currentTimeMillis()).toString();

        String hash = createHashForCallMarvelApi(ts);

        return restTemplate.getForObject(marvelComics,
                MarvelComicsResponse.class, characterId, limit, ts, marvelPublicKey, hash);

    }

    public MarvelComicsResponse callMarvelAPIForComics(String characterId, Long limit, String offset) {
        String ts = new Timestamp(System.currentTimeMillis()).toString();

        String hash = createHashForCallMarvelApi(ts);

        return restTemplate.getForObject(marvelComicsWithOffset, MarvelComicsResponse.class, characterId, limit, offset, ts, marvelPublicKey, hash);

    }

    public DialogFlowResponse callDialogFlowApi(DialogFlowRequest dialogFlowRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + dialogFlowClientToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(dialogFlowRequest, headers);
        try {
            return restTemplate.postForObject(dialogFlowUrl, entity, DialogFlowResponse.class);
        } catch (HttpClientErrorException e) {
//            System.out.println(e.getResponseBodyAsString());
            return null;
        }
    }

    private MarvelCharacterResponse callMarvelAPIForChatacterNameStartsWith(String characterName, String limit) {

        String newTs = new Timestamp(System.currentTimeMillis()).toString();
        String newHash = createHashForCallMarvelApi(newTs);

        return restTemplate.getForObject(marvelCharactersStartsWith,
                MarvelCharacterResponse.class, characterName, limit, newTs, marvelPublicKey, newHash);
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
}
