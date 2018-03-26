package Bot.DTO.Message;

import Bot.DTO.Elements.Attachment;

public class GenericMessage {
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

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
