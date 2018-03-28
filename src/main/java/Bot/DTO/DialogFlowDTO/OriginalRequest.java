package Bot.DTO.DialogFlowDTO;

import Bot.DTO.RequestDTO.Messaging;

public class OriginalRequest {
    private String source;
    private Messaging data;

    public OriginalRequest(String source, Messaging data) {
        this.source = source;
        this.data = data;
    }

    public OriginalRequest() {
        super();
    }

    public String getSource() {
        return source;
    }

    public Messaging getData() {
        return data;
    }

    public void setData(Messaging data) {
        this.data = data;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
