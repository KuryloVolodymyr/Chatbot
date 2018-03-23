package Bot.Util.Template;

import Bot.Util.Message.GenericMessage;
import Bot.Util.Recipient;

public class GenericMessageTemplate implements MessageTemplate{
    private Recipient recipient;
    private GenericMessage message;

    public GenericMessageTemplate() {
    }

    public GenericMessageTemplate(Recipient recipient, GenericMessage message) {
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public Recipient getRecipient() {
        return recipient;
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
