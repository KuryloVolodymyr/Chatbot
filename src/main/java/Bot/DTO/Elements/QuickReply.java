package Bot.DTO.Elements;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuickReply {
    @JsonProperty("content_type")
    private String type;
    private String title;
    private String payload;

    public QuickReply(String type, String title, String payload){
        this.type=type;
        this.title=title;
        this.payload = payload;
    }

    public QuickReply(){
        super();
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getPayload() {
        return payload;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
