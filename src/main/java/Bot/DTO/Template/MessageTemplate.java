package Bot.DTO.Template;

import Bot.DTO.Recipient;

public interface MessageTemplate {

    Recipient getRecipient();

    void setRecipient(Recipient recipient);
}
