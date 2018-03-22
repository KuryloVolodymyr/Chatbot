package Bot.Controller;

import Bot.Util.MessageBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/callback")
public class MainController {
    private final String appSecret;
    private final String verifyToken;
    private final String pageAccessToken;

    MessageBuilder messageBuilder = new MessageBuilder();

    @Autowired
    private RestTemplate restTemplate;


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
    public ResponseEntity<Void> bodyTest(@RequestBody final String info) {
        JSONObject jsonObject = new JSONObject(info);

        String senderPSID = jsonObject.getJSONArray("entry").getJSONObject(0).getJSONArray("messaging")
                .getJSONObject(0).getJSONObject("sender").getString("id");
        String recepientPSID = jsonObject.getJSONArray("entry").getJSONObject(0).getJSONArray("messaging")
                .getJSONObject(0).getJSONObject("recipient").getString("id");
        String textOFMessage = jsonObject.getJSONArray("entry").getJSONObject(0).getJSONArray("messaging")
                .getJSONObject(0).getJSONObject("message").getString("text");


        System.out.println(info);
        System.out.println(recepientPSID);
        System.out.println(senderPSID);
        System.out.println(textOFMessage);


        String responseString = messageBuilder.build(textOFMessage, senderPSID);
        System.out.println(responseString);


        JSONObject responceJSON = new JSONObject(responseString);
        System.out.println(responceJSON);

//        try {
//            callSendAPI(responseString);
//        } catch (HttpClientErrorException e) {
//            System.out.println("Http exception");
//        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    public ResponseEntity handleMessage(String senderPSID, ){
//
//    }
//    public ResponseEntity handlePostbacks(){
//
//    }
//
    public void callSendAPI(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(message, headers);
        System.out.println(entity);
        restTemplate.postForEntity("https://graph.facebook.com/v2.6/me/messages?access_token={}",
                entity, String.class, pageAccessToken);

    }


}
