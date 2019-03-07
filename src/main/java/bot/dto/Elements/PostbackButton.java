package bot.dto.Elements;

public class PostbackButton implements Button {
    private static final String POSTBACK = "postback";
    private String type;
    private String title;
    private String payload;

    public PostbackButton() {
    }

    public PostbackButton(String title, String payload) {
        this.type = POSTBACK;
        this.title = title;
        this.payload = payload;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
