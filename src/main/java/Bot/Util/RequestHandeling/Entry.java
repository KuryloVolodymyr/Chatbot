package Bot.Util.RequestHandeling;

import java.util.List;

public class Entry {
    long id;
    long time;
    List<Messaging> messaging;

    public Entry(long id, long time, List<Messaging> messaging){
        this.id = id;
        this.time = time;
        this.messaging = messaging;
    }

    public Entry(){
        super();
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
