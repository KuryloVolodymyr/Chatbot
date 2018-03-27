package Bot.DTO.RequestDTO;

public class QuickReply {
    String payload;

    public QuickReply(String payload){
        this.payload = payload;
    }

    public  QuickReply(){
        super();
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
