package Bot.Util.Elements;

public class ImagePayload {
    private String url;

    public ImagePayload(String url){
        this.url = url;
    }

    public ImagePayload(){
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
