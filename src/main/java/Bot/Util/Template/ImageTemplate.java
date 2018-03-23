package Bot.Util.Template;

import Bot.Util.Message.ImageMessage;
import Bot.Util.Recipient;

public class ImageTemplate implements MessageTemplate {
    private Recipient recipient;
    private ImageMessage message;

    public ImageTemplate(Recipient recipient, ImageMessage message) {
        this.recipient = recipient;
        this.message = message;
    }

    public ImageTemplate() {
        super();
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public ImageMessage getMessage() {
        return message;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public void setMessage(ImageMessage message) {
        this.message = message;
    }
}
