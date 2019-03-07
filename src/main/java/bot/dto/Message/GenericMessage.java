package bot.dto.Message;

import bot.dto.Elements.Attachment;
import bot.dto.Elements.QuickReply;

import java.util.List;

public class GenericMessage implements Message {
    private Attachment attachment;

    public GenericMessage(Attachment attachment){
        this.attachment = attachment;
    }

    public GenericMessage(){
        super();
    }

    public Attachment getAttachment() {
        return attachment;
    }

    @Override
    public String getText() {
        return null;
    }

//    @Override
//    public List<QuickReply> getQuickReplies() {
//        return null;
//    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
