package Bot.Util.Elements;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuickReply {
    @JsonProperty("content_type")
    String contentType;
    String title;
    String payload;

    public QuickReply(String contentType, String title, String payload){
        this.contentType = contentType;
        this.title = title;
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;  }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType){
        this.contentType = contentType;
    }

}

