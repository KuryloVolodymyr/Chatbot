package bot.dto.Broadcast;

import java.util.List;

public class BroadcastMessages {
    private List<Message> messages;

    public BroadcastMessages(){

    }

    public BroadcastMessages(List<Message> messages){
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
