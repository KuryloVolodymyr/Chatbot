package bot.dto.Elements;

public class ImagePayload implements Payload {
    private String url;

    public ImagePayload(String url) {
        this.url = url;
    }

    public ImagePayload() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getTemplateType() {
        return null;
    }

    @Override
    public void setTemplateType(String templateType) {

    }
}
