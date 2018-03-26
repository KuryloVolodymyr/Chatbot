package Bot.DTO.Template;

import Bot.DTO.Message.ImageMessage;
import Bot.DTO.Recipient;

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
