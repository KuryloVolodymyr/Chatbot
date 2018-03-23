package Bot.Util.Message;

import Bot.Util.Elements.QuickReply;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuickReplyMessage implements Message {

   String text;

   @JsonProperty("quick_replies")
   List<QuickReply> quickReplies;

   public QuickReplyMessage(String text, List<QuickReply> quickReplies){
       this.text = text;
       this.quickReplies = quickReplies;
   }
}
