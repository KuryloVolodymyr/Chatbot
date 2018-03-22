package Bot.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageBuilder {

    private JSONObject responce;
    private JSONObject messageObj;
    private JSONObject recipientObj;

    public String buildResponceForText(String userMessage, String recepientPSID) {
        responce = new JSONObject();
        messageObj = new JSONObject();
        recipientObj = new JSONObject();

        messageObj.put("text", userMessage);
        recipientObj.put("id", recepientPSID);
        responce.put("recipient", recipientObj);
        responce.put("message", messageObj);
        return responce.toString();
    }

    public String buildResponceForAttachment(String attachmentUrl, String recepientPSID) {
        return "{\n" +
                "\t\"recipient\":\n" +
                "\t\t{\"id\":\""+recepientPSID+"\" }, \n" +
                "\t\"message\":\n" +
                "\t{\n" +
                "      \"attachment\": {\n" +
                "        \"type\": \"template\",\n" +
                "        \"payload\": {\n" +
                "          \"template_type\": \"generic\",\n" +
                "          \"elements\": [{\n" +
                "            \"title\": \"Is this the right picture?\",\n" +
                "            \"subtitle\": \"Tap a button to answer.\",\n" +
                "            \"image_url\": \""+attachmentUrl+"\",\n" +
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
}

