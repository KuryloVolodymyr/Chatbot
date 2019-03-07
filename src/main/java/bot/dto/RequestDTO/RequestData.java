package bot.dto.RequestDTO;

import java.util.ArrayList;
import java.util.List;

public class RequestData {
    String object;
    List<Entry> entry;

    public RequestData(String object, List<Entry> entry) {
        this.object = object;
        this.entry = entry;
    }

    public RequestData() {
        super();
    }

    public static RequestData buildTextRequest(String resource) {
        RequestData data = new RequestData();
        data.setObject("page");

        data.setEntry(new ArrayList<>());
        data.getEntry().add(Entry.buildTextEntry(resource));
        return data;
    }

    public static RequestData buildPayloadRequest(Postback postback) {
        RequestData data = new RequestData();
        data.setObject("page");
        data.setEntry(new ArrayList<>());
        data.getEntry().add(Entry.buildPostbackEntry(postback));
        return data;
    }

    public static RequestData buildQuickReplyRequest(String qrText, String qrPayload) {
        RequestData data = new RequestData();
        data.setObject("page");
        data.setEntry(new ArrayList<>());
        data.getEntry().add(Entry.buildQuickReplyEntry(qrText, qrPayload));
        return data;
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

    public RequestData(String text) {
        this.object = "page";
        this.entry = new ArrayList<>();
        this.entry.add(Entry.buildTextEntry(text));
    }

}
