package Bot.Util.Template;

import Bot.Util.Message.TextMessage;
import Bot.Util.Recipient;

import java.io.Serializable;

public class TextMessageTemplate implements MessageTemplate, Serializable {
    private Recipient recipient;
    private TextMessage message;

    public TextMessageTemplate() {
    }

    public TextMessageTemplate(long id, String text) {
        this.recipient = new Recipient(id);
        this.message = new TextMessage(text);
    }

    public Recipient getRecipient() {
    return recipient;
}

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public TextMessage getMessage() {
        return message;
    }

    public void setMessage(TextMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return (recipient.toString());
    }
}
