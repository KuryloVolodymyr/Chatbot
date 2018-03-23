package Bot.Util.Template;

import Bot.Util.Elements.QuickReply;
import Bot.Util.Message.QuickReplyMessage;
import Bot.Util.Recipient;

public class QuickReplyTemplate implements MessageTemplate {
    private Recipient recipient;
    private QuickReplyMessage message;

    public QuickReplyTemplate(Recipient recipient, QuickReplyMessage quickReplyMessage){
        this.recipient = recipient;
        this.message = quickReplyMessage;
    }
    public QuickReplyTemplate(){
        super();
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public QuickReplyMessage getMessage() {
        return message;
    }

    public void setMessage(QuickReplyMessage message) {
        this.message = message;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
}
