package bot.dto.RequestDTO;

import bot.dto.Recipient;
import bot.dto.Sender;
import org.springframework.web.bind.annotation.PostMapping;

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

    public static Messaging buildSimpleTextMessaging(String text) {
        Messaging messaging = new Messaging();
        messaging.setSender(new Sender(123L));
        messaging.setRecipient(new Recipient(123L));
        messaging.setTimestamp(System.currentTimeMillis());
        messaging.setMessage(Message.buildTextMessage(text));
        return messaging;
    }

    public Messaging() {
        super();
    }

    public static Messaging buildPostbackMessaging(Postback postback) {
        Messaging messaging = new Messaging();
        messaging.setSender(new Sender(123L));
        messaging.setRecipient(new Recipient(123L));
        messaging.setTimestamp(System.currentTimeMillis());
        messaging.setPostback(postback);
        return messaging;
    }

    public static Messaging buildQuickReplyMessanging(String qrText, String qrPayload) {
        Messaging messaging = new Messaging();
        messaging.setSender(new Sender(123L));
        messaging.setRecipient(new Recipient(123L));
        messaging.setTimestamp(System.currentTimeMillis());
        messaging.setMessage(Message.buildQuickReplyMessage(qrText, qrPayload));
        return messaging;
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
