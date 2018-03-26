package Bot.DTO.RequestDTO;

import Bot.DTO.Recipient;
import Bot.DTO.Sender;

public class Messaging {
    private Sender sender;
    private Recipient recipient;
    private long timestamp;
    private Message message;
    private Postback postback;

    public Messaging(Sender sender, Recipient recipient, long timestamp, Message message, Postback postback) {
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = timestamp;
        this.message = message;
        this.postback = postback;
    }

    public Messaging(Sender sender, Recipient recipient, long timestamp, Message message) {
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = timestamp;
        this.message = message;
    }

    public Messaging(Sender sender, Recipient recipient, long timestamp, Postback postback) {
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = timestamp;
        this.postback = postback;
    }

    public Messaging() {
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

    public Postback getPostback() {
        return postback;
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

    public void setPostback(Postback postback) {
        this.postback = postback;
    }
}
