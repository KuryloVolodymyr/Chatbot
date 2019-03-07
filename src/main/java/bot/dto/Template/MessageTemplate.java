package bot.dto.Template;

import bot.dto.Message.*;
import bot.dto.Recipient;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.aspectj.weaver.ast.Test;

public class MessageTemplate {

    private Recipient recipient;
    @JsonProperty("messaging_type")
    private String messagingType;
    private Message message;
//    private TestMessage testMessage;

    public MessageTemplate(long id, GenericMessage genericMessage) {
        this.recipient = new Recipient(id);
        this.messagingType = "RESPONSE";
        this.message = genericMessage;
    }

    public MessageTemplate(Recipient recipient, ImageMessage message) {
        this.recipient = recipient;
        this.messagingType = "RESPONSE";
        this.message = message;
    }

    public MessageTemplate(Recipient recipient, QuickReplyMessage quickReplyMessage) {
        this.recipient = recipient;
        this.messagingType = "RESPONSE";
        this.message = quickReplyMessage;
    }

    public MessageTemplate(long id, String text) {
        this.recipient = new Recipient(id);
        this.messagingType = "RESPONSE";
        this.message = new TextMessage(text);
    }


    public MessageTemplate() {
        super();
    }

    public Recipient getRecipient() {
        return this.recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
