package Bot.Util.Template;

import Bot.Util.Recipient;

public interface MessageTemplate {

    Recipient getRecipient();

    void setRecipient(Recipient recipient);
}
