package Bot.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {

    @Autowired
    private RestTemplate restTemplate;

    private JSONObject responce;
    private JSONObject messageObj;
    private JSONObject recipientObj;

    private String buildResponceForText(String userMessage, String recepientPSID) {
        responce = new JSONObject();
        messageObj = new JSONObject();
        recipientObj = new JSONObject();

        messageObj.put("text", userMessage);
        recipientObj.put("id", recepientPSID);
        responce.put("recipient", recipientObj);
        responce.put("message", messageObj);
        return responce.toString();
    }

    private String buildResponceForAttachment(String attachmentUrl, String recepientPSID) {
        return "{\n" +
                "\t\"recipient\":\n" +
                "\t\t{\"id\":\"" + recepientPSID + "\" }, \n" +
                "\t\"message\":\n" +
                "\t{\n" +
                "      \"attachment\": {\n" +
                "        \"type\": \"template\",\n" +
                "        \"payload\": {\n" +
                "          \"template_type\": \"generic\",\n" +
                "          \"elements\": [{\n" +
                "            \"title\": \"Is this the right picture?\",\n" +
                "            \"subtitle\": \"Tap a button to answer.\",\n" +
                "            \"image_url\": \"" + attachmentUrl + "\",\n" +
                "            \"buttons\": [\n" +
                "              {\n" +
                "                \"type\": \"postback\",\n" +
                "                \"title\": \"Yes!\",\n" +
                "                \"payload\": \"yes\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"type\": \"postback\",\n" +
                "                \"title\": \"No!\",\n" +
                "                \"payload\": \"no\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }]\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n";
    }

    public void handleMessage(JSONObject jsonObject, String senderPSID) {
        JSONObject messageObj = jsonObject.getJSONArray("entry").getJSONObject(0).getJSONArray("messaging")
                .getJSONObject(0).getJSONObject("message");

        if (messageObj.has("text")) {
            String textOfMessage = messageObj.getString("text");
            String responce = conversation(textOfMessage);
            handleTextMessage(responce, senderPSID);
        } else if (messageObj.has("attachments")) {
            String imageURL = messageObj.getJSONArray("attachments").getJSONObject(0)
                    .getJSONObject("payload").getString("url");
            handleAttachmentMessage(imageURL, senderPSID);
        }
    }

    private void handleTextMessage(String text, String senderPSID) {
        callSendAPI(buildResponceForText(text, senderPSID));
    }

    private void handleAttachmentMessage(String attachmentURL, String senderPSID) {
        callSendAPI(buildResponceForAttachment(attachmentURL, senderPSID));
    }

    public void handlePostbacks(JSONObject jsonObject, String senderPSID) {

        String payloadObj = jsonObject.getJSONArray("entry").getJSONObject(0)
                .getJSONArray("messaging").getJSONObject(0).getJSONObject("postback").getString("payload");
        if (payloadObj.equals("yes"))
            callSendAPI(buildResponceForText("I`m glad to help you", senderPSID));
        else if (payloadObj.equals("no")) {
            callSendAPI(buildResponceForText("but you`ve send it", senderPSID));
        }
    }

    private void callSendAPI(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(message, headers);
        System.out.println(entity);
        restTemplate.postForObject("https://graph.facebook.com/v2.6/me/messages?access_token=EAAcUEIGl26QBAEtCLShv7xYRLgtid2XWXZCcUkZBn7RCOFTiUgEoQgq2Kxn7jApCEwxc4W7r1PMiBkf6kdfdIVytxBUoqellqk9B3OqZB0qNO9uSxHa3hybwLxMH9ZBCSs1IoD1YeyYQoCS8TPNJmy9f7RlIqz2VlyZAuogsAqwZDZD",
                entity, String.class);

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
            default :
                response = message;
        }
        return response;

    }
}
