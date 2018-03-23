package Bot.Util.Template;

import Bot.Util.Elements.QuickReply;
import Bot.Util.Message.QuickReplyMessage;
import Bot.Util.Recipient;

import java.util.List;

public class QuickReplyTemplate implements MessageTemplate {
    private Recipient recipient;
    private QuickReplyMessage message;

    public QuickReplyTemplate(long id, String text, List<QuickReply> quickReplies) {
        this.recipient = new Recipient(id);
        this.message = new QuickReplyMessage(text, quickReplies);
    }

    @Override
    public Recipient getRecipient() {
        return null;
    }

    @Override
    public void setRecipient(Recipient recipient) {

    }
}
