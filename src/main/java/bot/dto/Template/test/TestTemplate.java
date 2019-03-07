package bot.dto.Template.test;

import bot.dto.Message.*;
import bot.dto.Recipient;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestTemplate {


    private Recipient recipient;
    @JsonProperty("messaging_type")
    private String messagingType;
//    private Message message;
    private TestMessage message;

    public TestTemplate(long id, TestMessage message) {
        this.recipient = new Recipient(id);
        this.messagingType = "RESPONSE";
        this.message = message;
    }


    public TestTemplate() {
        super();
    }

    public Recipient getRecipient() {
        return this.recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public TestMessage getMessage() {
        return message;
    }

    public void setMessage(TestMessage message) {
        this.message = message;
    }

}
