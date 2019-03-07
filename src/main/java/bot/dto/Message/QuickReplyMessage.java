package bot.dto.Message;

import bot.dto.Elements.Attachment;
import bot.dto.Elements.QuickReply;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuickReplyMessage implements Message {
    private String text;
    @JsonProperty("quick_replies")
    private List<QuickReply> quickReplies;

    public QuickReplyMessage(String text, List<QuickReply> quickReplies){
        this.text = text;
        this.quickReplies = quickReplies;
    }
    public QuickReplyMessage(){
        super();
    }

    @Override
    public Attachment getAttachment() {
        return null;
    }

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
}
