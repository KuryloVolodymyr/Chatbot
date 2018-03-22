package Bot.Util;

import org.json.JSONException;

public class MessageBuilder {

    public String build(String userMessage, String recepientPSID) {
        return "{\n" +
                "\t\"recipient\":\n" +
                "\t\t{\"id\":\"" + recepientPSID + "\" }, \n" +
                "\t\"message\":\n" +
                "\t\t{ \"text\":\"" + userMessage + "\" }\n" +
                "}";

    }
}
