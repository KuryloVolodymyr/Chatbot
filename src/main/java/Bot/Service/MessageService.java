package Bot.Service;

import Bot.Util.Elements.QuickReply;
import Bot.Util.RequestHandeling.RequestHandler;
import Bot.Util.Template.MessageTemplate;
import Bot.Util.Template.QuickReplyTemplate;
import Bot.Util.Template.TextMessageTemplate;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private RestTemplate restTemplate;

    private JSONObject responce;
    private JSONObject messageObj;
    private JSONObject recipientObj;

    public static final String GENERIC = "generic";
    public static final String PAGE_ACCESS_TOKEN = "PAGE_ACCESS_TOKEN";
    public static final String $_ENTRY = "$.entry";
    public static final String OPTIN = "optin";
    public static final String MESSAGE = "message";
    public static final String DELIVERY = "delivery";
    public static final String POSTBACK = "postback";
    public static final String READ = "read";
    public static final String ACCOUNT_LINKING = "account_linking";
    public static final String $_SENDER_ID = "$.sender.id";
    public static final String $_POSTBACK_PAYLOAD = "$.postback.payload";
    public static final String $_RECIPIENT_ID = "$.recipient.id";
    public static final String $_DELIVERY = "$.delivery";
    public static final String MIDS = "mids";
    public static final String WATERMARK = "watermark";
    public static final String SEQ = "seq";
    public static final String $_MESSAGE_MID = "$.message.mid";
    public static final String $_MESSAGE_APP_ID = "$.message.app_id";
    public static final String $_MESSAGE_TEXT = "$.message.text";
    public static final String $_MESSAGE = "$.message";
    public static final String $_MESSAGE_IS_ECHO = "$.message.is_echo";


    private void callSendAPI(MessageTemplate message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(message, headers);
        String responceFromSendAPI = restTemplate.postForObject("https://graph.facebook.com/v2.6/me/messages?access_token=EAAcUEIGl26QBAEtCLShv7xYRLgtid2XWXZCcUkZBn7RCOFTiUgEoQgq2Kxn7jApCEwxc4W7r1PMiBkf6kdfdIVytxBUoqellqk9B3OqZB0qNO9uSxHa3hybwLxMH9ZBCSs1IoD1YeyYQoCS8TPNJmy9f7RlIqz2VlyZAuogsAqwZDZD",
                entity, String.class);
        System.out.println(responceFromSendAPI);

    }

    private String conversation(String message) {
        String response;
        switch (message.toLowerCase()) {
            case "hi":
                response = "Hi, I`m Marvel bot, I`ll tell you everithing you want to know about your favorite Marvel superheroes. If you need some help, just type \"help\"";
                break;
            case "hello":
                response = "Hello, I`m Marvel bot, I`ll tell you everithing you want to know about your favorite Marvel superheroes. If you need some help, just type \"help\"";
                break;
            case "sup":
                response = "Sup, I`m Marvel bot, I`ll tell you everithing you want to know about your favorite Marvel superheroes. If you need some help, just type \"help\"";
                break;
            case "help":
                response = ""; // TODO
            default:
                response = message;
        }
        return response;

    }

    private boolean isMessageTypeOf(Map<String, Object> m, String message) {
        return m.get(message) != null;
    }


    private boolean isEcho(Map<String, Object> m) {
        return isMessageTypeOf(JsonPath.read(m, $_MESSAGE), "is_echo")
                && (Boolean) JsonPath.read(m, $_MESSAGE_IS_ECHO);
    }

    public void processMessage(RequestHandler request) {

        long id = request.getEntry().get(0).getMessaging().get(0).getSender().getId();
        String text = request.getEntry().get(0).getMessaging().get(0).getMessage().getText();
        MessageTemplate template = new TextMessageTemplate(id, text);
        callSendAPI(template);
    }



}
