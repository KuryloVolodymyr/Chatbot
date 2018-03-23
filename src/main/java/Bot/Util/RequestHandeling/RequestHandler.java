package Bot.Util.RequestHandeling;

import java.util.List;

public class RequestHandler {
    String object;
    List<Entry> entry;

    public RequestHandler(String object, List<Entry> entry){
        this.object = object;
        this.entry = entry;
    }
    public RequestHandler(){
        super();
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public String getObject() {
        return object;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
