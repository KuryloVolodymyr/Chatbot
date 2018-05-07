package Bot.DTO.Template;

import Bot.DTO.Elements.GenericPayload;
import Bot.DTO.Message.GenericMessage;
import Bot.DTO.Recipient;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericMessageTemplate implements MessageTemplate {

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
