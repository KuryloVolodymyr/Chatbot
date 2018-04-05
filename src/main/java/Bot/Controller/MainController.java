package Bot.Controller;

import Bot.DTO.RequestDTO.Entry;
import Bot.DTO.RequestDTO.Messaging;
import Bot.DTO.RequestDTO.RequestData;
import Bot.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


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
                          @Value("${pageAccessToken}") final String pageAccessToken) {
        this.appSecret = appSecret;
        this.verifyToken = verifyToken;
        this.pageAccessToken = pageAccessToken;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.verify_token") String token,
                                                @RequestParam("hub.challenge") String challenge,
                                                @RequestParam("hub.mode") String mode) {
        if (token.equals(verifyToken)) {
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> conversation(@RequestBody RequestData data) {

//        System.out.println(data);

        for (Entry entry : data.getEntry()) {
                for (Messaging request : entry.getMessaging()) {
                messageService.processRequest(request);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
