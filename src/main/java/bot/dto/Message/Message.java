package bot.dto.Message;

import bot.dto.Elements.Attachment;
import bot.dto.Elements.ImageAttachment;
import bot.dto.Elements.QuickReply;

import java.util.List;

public interface Message {

//    private String text;
//    private Attachment attachment;
//    private List<QuickReply> quickReplies;

    public Attachment getAttachment() ;

    public String getText() ;
}
