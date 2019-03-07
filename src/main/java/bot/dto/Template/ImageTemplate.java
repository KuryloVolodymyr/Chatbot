package bot.dto.Template;

import bot.dto.Message.ImageMessage;
import bot.dto.Recipient;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageTemplate extends MessageTemplate {
    private Recipient recipient;
    @JsonProperty("messaging_type")
    private String messagingType;
    private ImageMessage message;

    public ImageTemplate(Recipient recipient, ImageMessage message) {
        this.recipient = recipient;
        this.messagingType = "RESPONSE";
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
