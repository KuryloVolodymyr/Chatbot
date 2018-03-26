package Bot.DTO.RequestDTO;


public class Attachments {
    String type;
    Payload payload;

    public Attachments(String type, Payload payload){
        this.type = type;
        this.payload = payload;
    }

    public Attachments(){super();}


    public String getType() {
        return type;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public void setType(String type) {
        this.type = type;
    }
}
