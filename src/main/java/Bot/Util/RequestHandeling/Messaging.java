package Bot.Util.RequestHandeling;

import Bot.Util.Recipient;
import Bot.Util.Sender;

public class Messaging {
    Sender sender;
    Recipient recipient;
    long timestamp;
    Message message;

    public Messaging(Sender sender, Recipient recipient, long timestamp, Message message){
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = timestamp;
        this.message = message;
    }

    public Messaging(){
        super();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Message getMessage() {
        return message;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public Sender getSender() {
        return sender;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
