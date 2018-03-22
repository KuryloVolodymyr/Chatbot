package Bot.Controller;

import Bot.Service.MessageService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/callback")
public class MainController {
    private final String appSecret;
    private final String verifyToken;
    private final String pageAccessToken;


    @Autowired
    MessageService messageService;


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
    public ResponseEntity<Void> conversation(@RequestBody final String info) {
        JSONObject jsonObject = new JSONObject(info);

        String senderPSID = jsonObject.getJSONArray("entry").getJSONObject(0)
                .getJSONArray("messaging").getJSONObject(0)
                .getJSONObject("sender").getString("id");

//        String recepientPSID = jsonObject.getJSONArray("entry").getJSONObject(0)
//                .getJSONArray("messaging").getJSONObject(0)
//                .getJSONObject("recipient").getString("id");

        if (jsonObject.getJSONArray("entry").getJSONObject(0).getJSONArray("messaging")
                .getJSONObject(0).has("message")) {

            messageService.handleMessage(jsonObject, senderPSID);
        } else {
            messageService.handlePostbacks(jsonObject, senderPSID);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }


}
