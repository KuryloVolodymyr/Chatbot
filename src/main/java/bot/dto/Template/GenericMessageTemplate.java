package bot.dto.Template;

import bot.dto.Message.GenericMessage;
import bot.dto.Recipient;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericMessageTemplate extends MessageTemplate {

    private Recipient recipient;
    @JsonProperty("messaging_type")
    private String messagingType;
    private GenericMessage message;

    public GenericMessageTemplate(long id, GenericMessage genericMessage) {
        this.recipient = new Recipient(id);
        this.messagingType = "RESPONSE";
        this.message = genericMessage;
    }

    public GenericMessageTemplate() {
        super();
    }

    @Override
    public Recipient getRecipient() {
        return this.recipient;
    }

    @Override
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public GenericMessage getMessage() {
        return message;
    }

    public void setMessage(GenericMessage message) {
        this.message = message;
    }
}
