package Bot.Service;

import Bot.Util.RequestHandeling.RequestHandler;
import Bot.Util.Template.MessageTemplate;
import Bot.Util.Template.TextMessageTemplate;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private RestTemplate restTemplate;

    public void callSendAPI(MessageTemplate message) {
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
//            case "help":
//                response = ""; // TODO
            default:
                response = message;
        }
        return response;

    }

    private boolean isMessageTypeOf(Map<String, Object> m, String message) {
        return m.get(message) != null;
    }


    public void processMessage(RequestHandler request) {

        long id = request.getEntry().get(0).getMessaging().get(0).getSender().getId();
        String text = request.getEntry().get(0).getMessaging().get(0).getMessage().getText();
        MessageTemplate template = new TextMessageTemplate(id, text);
        callSendAPI(template);
    }


}
