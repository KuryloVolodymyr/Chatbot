package bot.dto.Broadcast;

public class Message {
    private DynamicText dynamic_text;

    public Message(DynamicText dynamic_text) {
        this.dynamic_text = dynamic_text;
    }

    public Message() {
    }


    public DynamicText getDynamic_text() {
        return dynamic_text;
    }

    public void setDynamic_text(DynamicText dynamic_text) {
        this.dynamic_text = dynamic_text;
    }

}
