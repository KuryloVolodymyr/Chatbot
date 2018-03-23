package Bot.Controller;

import Bot.Service.MessageService;
import Bot.Util.Elements.QuickReply;
import Bot.Util.MappingTest;
import Bot.Util.Message.Message;
import Bot.Util.Message.QuickReplyMessage;
import Bot.Util.Message.TextMessage;
import Bot.Util.Recipient;
import Bot.Util.RequestHandeling.RequestHandler;
import Bot.Util.Template.MessageTemplate;
import Bot.Util.Template.QuickReplyTemplate;
import Bot.Util.Template.TextMessageTemplate;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> conversation(@RequestBody RequestHandler data) {

        QuickReply quickReply = new QuickReply("text", "text", "text");
        List<QuickReply> quickReplies = new ArrayList<>();
        quickReplies.add(quickReply);
        Recipient recipient = new Recipient(data.getEntry().get(0).getMessaging().get(0).getSender().getId());
        QuickReplyMessage quickReplyMessage = new QuickReplyMessage("text", quickReplies);
        MessageTemplate template = new QuickReplyTemplate(recipient, quickReplyMessage);

        messageService.callSendAPI(template);
//
//        messageService.processMessage(data);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
