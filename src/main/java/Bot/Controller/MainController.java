package Bot.Controller;

import Bot.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;


@RestController
@RequestMapping("/callback")
public class MainController {
    private final String appSecret;
    private final String verifyToken;
    private final String pageAccessToken;


    @Autowired
    MessageService messageService;

    @Autowired
    RestTemplate restTemplate;


    public MainController(@Value("${appSecret}") final String appSecret,
                          @Value("${verifyToken}") final String verifyToken,
                          @Value("@{pageAccessToken}") final String pageAccessToken) {
        this.appSecret = appSecret;
        this.verifyToken = verifyToken;
        this.pageAccessToken = pageAccessToken;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.verify_token") final String token,
                                                @RequestParam("hub.challenge") final String challenge,
                                                @RequestParam("hub.mode") final String mode) {
        System.out.println("Challenge " + challenge);
        return new ResponseEntity<>(challenge, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> conversation(@RequestBody String data) throws NoSuchAlgorithmException {

        System.out.println(data);

        callMarvelAPI();
//        messageService.processMessage(data);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    public void callMarvelAPI() throws NoSuchAlgorithmException {

        String privateKey = "8dbde837f7e5a70f35594ef3323315d7c6836a4a";
        String publicKey = "b7860d0937d6fc64eaf8acc15ddbacc2";
        String ts = new Timestamp(System.currentTimeMillis()).toString();
        MessageDigest md = MessageDigest.getInstance("MD5");

        String toHash = ts + privateKey + publicKey;

        String hash;


        md.update(toHash.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        hash = sb.toString();

        String marvelResponce = restTemplate.getForObject("https://gateway.marvel.com:443/v1/public/characters?name=Spider-Man&ts={ts}&apikey={key}&hash={hash}",
                String.class, ts, publicKey , hash);
        System.out.println(marvelResponce);
    }
}
