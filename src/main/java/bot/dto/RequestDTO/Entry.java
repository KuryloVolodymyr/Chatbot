package bot.dto.RequestDTO;

import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Entry {
    long id;
    long time;
    List<Messaging> messaging;

    public Entry(long id, long time, List<Messaging> messaging) {
        this.id = id;
        this.time = time;
        this.messaging = messaging;
    }

    public Entry() {
        super();
    }

    public static Entry buildTextEntry(String resource) {
        Entry entry = new Entry();
        entry.setId(123);
        entry.setTime(System.currentTimeMillis());
        entry.setMessaging(new ArrayList<>());
        entry.getMessaging().add(Messaging.buildSimpleTextMessaging(resource));
        return entry;
    }

    public static Entry buildPostbackEntry(Postback postback) {
        Entry entry = new Entry();
        entry.setId(123);
        entry.setTime(System.currentTimeMillis());
        entry.setMessaging(new ArrayList<>());
        entry.getMessaging().add(Messaging.buildPostbackMessaging(postback));
        return entry;
    }

    public static Entry buildQuickReplyEntry(String qrText, String qrPayload) {
        Entry entry = new Entry();
        entry.setId(123);
        entry.setTime(System.currentTimeMillis());
        entry.setMessaging(new ArrayList<>());
        entry.getMessaging().add(Messaging.buildQuickReplyMessanging(qrText, qrPayload));
        return entry;
    }

    public List<Messaging> getMessaging() {
        return messaging;
    }

    public long getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMessaging(List<Messaging> messaging) {
        this.messaging = messaging;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
