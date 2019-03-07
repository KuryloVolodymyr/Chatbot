package bot.dto.Broadcast;

public class MessageIdObject {
    private Long message_creative_id;

    public MessageIdObject(Long message_creative_id){
        this.message_creative_id = message_creative_id;
    }

    public MessageIdObject(){

    }

    public Long getMessage_creative_id() {
        return message_creative_id;
    }

    public void setMessage_creative_id(Long message_creative_id) {
        this.message_creative_id = message_creative_id;
    }
}
