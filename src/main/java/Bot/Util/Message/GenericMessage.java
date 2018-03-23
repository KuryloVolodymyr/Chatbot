package Bot.Util.Message;

import Bot.Util.Elements.Attachment;

import java.io.Serializable;

public class GenericMessage implements Message, Serializable {
    private Attachment attachment;

    public GenericMessage() {
    }

    public GenericMessage(Attachment attachment) {
        this.attachment = attachment;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
