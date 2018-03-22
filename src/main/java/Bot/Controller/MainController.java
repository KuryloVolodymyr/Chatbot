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

            JSONObject messageObj = jsonObject.getJSONArray("entry").getJSONObject(0).getJSONArray("messaging")
                    .getJSONObject(0).getJSONObject("message");

            if (messageObj.has("text")) {
                String textOfMessage = messageObj.getString("text");

                handleTextMessage(textOfMessage, senderPSID);
            } else if (messageObj.has("attachments")) {
                String imageURL = messageObj.getJSONArray("attachments").getJSONObject(0)
                        .getJSONObject("payload").getString("url");
                handleAttachmentMessage(imageURL, senderPSID);
            }

        }
        else{
            String payloadObj = jsonObject.getJSONArray("entry").getJSONObject(0)
                    .getJSONArray("messaging").getJSONObject(0).getJSONObject("postback").getString("payload");
            if(payloadObj.equals("yes"))
                callSendAPI(messageBuilder.buildResponceForText("I`m glad to help you", senderPSID));
            else if(payloadObj.equals("no")){
                callSendAPI(messageBuilder.buildResponceForText("but you`ve send it", senderPSID));
            }

            System.out.println(payloadObj);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void handleTextMessage(String text, String senderPSID) {
        callSendAPI(messageBuilder.buildResponceForText(text, senderPSID));
    }

    private void handleAttachmentMessage(String attachmentURL, String senderPSID) {
        callSendAPI(messageBuilder.buildResponceForAttachment(attachmentURL, senderPSID));
    }
//    public ResponseEntity handlePostbacks(){
//
//    }

    private void callSendAPI(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(message, headers);
        System.out.println(entity);
        restTemplate.postForObject("https://graph.facebook.com/v2.6/me/messages?access_token=EAAcUEIGl26QBAEtCLShv7xYRLgtid2XWXZCcUkZBn7RCOFTiUgEoQgq2Kxn7jApCEwxc4W7r1PMiBkf6kdfdIVytxBUoqellqk9B3OqZB0qNO9uSxHa3hybwLxMH9ZBCSs1IoD1YeyYQoCS8TPNJmy9f7RlIqz2VlyZAuogsAqwZDZD",
                entity, String.class);

    }


}
