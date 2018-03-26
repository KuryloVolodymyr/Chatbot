package Bot.DTO.RequestDTO;

public class Postback {
    private String payload;
    private String title;

    public Postback(String payload, String title){
        this.payload = payload;
        this.title = title;
    }

    public Postback(){
        super();
    }

    public String getPayload() {
        return payload;
    }

    public String getTitle() {
        return title;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
