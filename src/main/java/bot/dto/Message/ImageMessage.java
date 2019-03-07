package bot.dto.Message;

import bot.dto.Elements.Attachment;
import bot.dto.Elements.ImageAttachment;
import bot.dto.Elements.QuickReply;

import java.util.List;

public class ImageMessage implements Message{
    private ImageAttachment attachment;

    public ImageMessage(ImageAttachment attachment) {
        this.attachment = attachment;
    }

    public ImageMessage() {
        super();
    }

    public ImageAttachment getImageAttachment() {
        return attachment;
    }

    public void setAttachment(ImageAttachment attachment) {
        this.attachment = attachment;
    }

    @Override
    public Attachment getAttachment() {
        return null;
    }

    @Override
    public String getText() {
        return null;
    }

//    @Override
//    public List<QuickReply> getQuickReplies() {
//        return null;
//    }
}
