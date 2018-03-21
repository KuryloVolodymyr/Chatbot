package Bot.Controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/callback")
public class MainController {
    private final String appSecret;
    private final String verifyToken;
    private final String pageAccessToken;


    public MainController(@Value("${appSecret}") final String appSecret,
                          @Value("${verifyToken}") final String verifyToken,
                          @Value("@{pageAccessToken}") final String pageAccessToken){
        this.appSecret = appSecret;
        this.verifyToken = verifyToken;
        this.pageAccessToken = pageAccessToken;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.verify_token") final String token,
                                                @RequestParam("hub.challenge") final String challenge,
                                                @RequestParam("hub.mode") final String mode) {
        return new ResponseEntity<>(challenge ,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> bodyTest(@RequestBody final String info){
        JSONObject jsonObject = new JSONObject(info);
        System.out.println(info);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    public ResponseEntity handleMessage(){
//
//    }
//    public ResponseEntity handlePostbacks(){
//
//    }
//
//    public ResponseEntity callSendAPI(){
//
//    }



}
