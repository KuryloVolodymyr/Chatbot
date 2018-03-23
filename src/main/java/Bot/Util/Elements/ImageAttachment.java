package Bot.Util.Elements;

public class ImageAttachment {
    private String type;
    private ImagePayload payload;

    public ImageAttachment(ImagePayload imagePayload) {
        this.type = "image";
        this.payload = imagePayload;
    }

    public ImageAttachment() {
        super();
    }

    public String getType() {
        return type;
    }

    public ImagePayload getPayload() {
        return payload;
    }

    public void setPayload(ImagePayload payload) {
        this.payload = payload;
    }

    public void setType(String type) {
        this.type = type;
    }
}
