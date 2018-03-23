package Bot.Util.Elements;

public class LinkButton implements Button {
    public static final String WEB_URL = "web_url";
    private String type;
    private String title;
    private String url;

    public LinkButton() {
    }

    public LinkButton(String title, String payload) {
        this.type = WEB_URL;
        this.title = title;
        this.url = payload;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
