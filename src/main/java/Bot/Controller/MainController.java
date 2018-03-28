package Bot.Controller;

import Bot.DTO.DialogFlowTest;
import Bot.DTO.Elements.*;
import Bot.DTO.Message.GenericMessage;
import Bot.DTO.RequestDTO.RequestHandler;
import Bot.DTO.Template.GenericMessageTemplate;
import Bot.DTO.Template.MessageTemplate;
import Bot.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


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
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.verify_token") final String token,
                                                @RequestParam("hub.challenge") final String challenge,
                                                @RequestParam("hub.mode") final String mode) {
        System.out.println("Challenge " + challenge);
        return new ResponseEntity<>(challenge, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpEntity>/*<Void>*/ conversation(@RequestBody RequestHandler data) {


//        System.out.println(data);
//        DialogFlowTest dialogFlowResponce = new DialogFlowTest("EVERYTHING FINE", "EVERYTHING FINE");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> entity = new HttpEntity<>(dialogFlowResponce, headers);

        messageService.processMessage(data.getEntry().get(0).getMessaging().get(0));
        return new ResponseEntity<>(HttpStatus.OK);
//

//        return new ResponseEntity<>(HttpStatus.OK);
    }

}
