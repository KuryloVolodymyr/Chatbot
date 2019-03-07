package bot.dto.Message;

import bot.dto.Elements.Attachment;
import bot.dto.Elements.QuickReply;

import java.util.List;

public class TextMessage implements Message{
    private String text;

    public TextMessage() {
    }

    public TextMessage(String text) {
        this.text = text;
    }

    @Override
    public Attachment getAttachment() {
        return null;
    }

    public String getText() {
        return text;
    }

//    @Override
//    public List<QuickReply> getQuickReplies() {
//        return null;
//    }

    public void setText(String text) {
        this.text = text;
    }
}
