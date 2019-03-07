package bot.dto.Message;

import bot.dto.Elements.Attachment;
import bot.dto.Elements.QuickReply;
import bot.dto.RequestDTO.test.TestAttachment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestMessage {
    private String text;
    @JsonProperty("quick_replies")
    private List<QuickReply> quickReplies;
    private TestAttachment attachment;


    public String getText() {
        return text;
    }

    public List<QuickReply> getQuickReplies() {
        return quickReplies;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setQuickReplies(List<QuickReply> quickReplies) {
        this.quickReplies = quickReplies;
    }

    public TestAttachment getAttachment() {
        return attachment;
    }

    public void setAttachment(TestAttachment attachment) {
        this.attachment = attachment;
    }
}
