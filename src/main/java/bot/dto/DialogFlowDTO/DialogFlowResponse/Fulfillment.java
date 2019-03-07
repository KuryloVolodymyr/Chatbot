package bot.dto.DialogFlowDTO.DialogFlowResponse;

import java.util.List;

public class Fulfillment {
    private String speech;
    private List<Message> messages;

    public Fulfillment(String speech, List<Message> messages){
        this.speech = speech;
        this.messages = messages;
    }

    public Fulfillment(){super();}

    public String getSpeech() {
        return speech;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
