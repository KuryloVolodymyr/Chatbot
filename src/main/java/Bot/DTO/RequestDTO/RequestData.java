package Bot.DTO.RequestDTO;

import java.util.List;

public class RequestData {
    String object;
    List<Entry> entry;

    public RequestData(String object, List<Entry> entry){
        this.object = object;
        this.entry = entry;
    }
    public RequestData(){
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
