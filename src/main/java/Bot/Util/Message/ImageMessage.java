package Bot.Util.Message;

import Bot.Util.Elements.ImageAttachment;

public class ImageMessage {
    private ImageAttachment attachment;

    public ImageMessage(ImageAttachment attachment) {
        this.attachment = attachment;
    }

    public ImageMessage() {
        super();
    }

    public ImageAttachment getAttachment() {
        return attachment;
    }

    public void setAttachment(ImageAttachment attachment) {
        this.attachment = attachment;
    }
}
