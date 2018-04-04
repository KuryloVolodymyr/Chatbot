package Bot.Service;

import Bot.DTO.DialogFlowDTO.DialogFlowRequest.DialogFlowRequest;
import Bot.DTO.DialogFlowDTO.DialogFlowResponse.DialogFlowResponse;
import Bot.DTO.MarvelDTO.MarvelCharacterlResponse;
import Bot.DTO.MarvelDTO.MarvelComicsResponce;
import Bot.DTO.Template.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

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
    private String sendAPIURL;

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

    @Value("${dialogFlow.developerAccessToken}")
    private String dialogFlowDeveloperToken;


    public void callSendAPI(MessageTemplate message) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(message, headers);

        restTemplate.postForObject(sendAPIURL, entity, String.class, pageAccessToken);
    }

    public MarvelCharacterlResponse callMarvelAPIForCharacter(String characterName) throws HttpClientErrorException {

        String limit = numberOfCharactersPerRequest;
        MarvelCharacterlResponse marvelCharacterlResponse;
        String ts = new Timestamp(System.currentTimeMillis()).toString();

        String hash = createHashForCallMarvelApi(ts);

        marvelCharacterlResponse = restTemplate.getForObject(marvelCharactersByNameURL, MarvelCharacterlResponse.class,
                characterName, limit, ts, marvelPublicKey, hash);

        if (marvelCharacterlResponse.getData().getResults().isEmpty()) {
            marvelCharacterlResponse = callMarvelAPIForChatacterNameStartsWith(characterName, limit);
        }
        return marvelCharacterlResponse;
    }

    public MarvelComicsResponce callMarvelAPIForComics(String characterId, Long limit) throws HttpClientErrorException {
        String ts = new Timestamp(System.currentTimeMillis()).toString();

        String hash = createHashForCallMarvelApi(ts);

        return restTemplate.getForObject(marvelComics,
                MarvelComicsResponce.class, characterId, limit, ts, marvelPublicKey, hash);

    }

    public MarvelComicsResponce callMarvelAPIForComics(String characterId, Long limit, String offset) throws HttpClientErrorException {
        String ts = new Timestamp(System.currentTimeMillis()).toString();

        String hash = createHashForCallMarvelApi(ts);

        return restTemplate.getForObject(marvelComicsWithOffset, MarvelComicsResponce.class, characterId, limit, offset, ts, marvelPublicKey, hash);

    }

    public DialogFlowResponse callDialogFlowApi(DialogFlowRequest dialogFlowRequest) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + dialogFlowClientToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(dialogFlowRequest, headers);
        return restTemplate.postForObject(dialogFlowUrl, entity, DialogFlowResponse.class);
    }

    private MarvelCharacterlResponse callMarvelAPIForChatacterNameStartsWith(String characterName, String limit) {

        String newTs = new Timestamp(System.currentTimeMillis()).toString();
        String newHash = createHashForCallMarvelApi(newTs);

        return restTemplate.getForObject(marvelCharactersStartsWith,
                MarvelCharacterlResponse.class, characterName, limit, newTs, marvelPublicKey, newHash);
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
