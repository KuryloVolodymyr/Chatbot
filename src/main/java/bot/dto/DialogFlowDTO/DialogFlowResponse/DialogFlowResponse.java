package bot.dto.DialogFlowDTO.DialogFlowResponse;

public class DialogFlowResponse {
    private String id;
    private String timestamp;
    private String lang;
    private Result result;
    private Status status;
    private String sessionId;

    public DialogFlowResponse(String id, String timestamp, String lang, Result result, Status status, String sessionId) {
        this.id = id;
        this.timestamp = timestamp;
        this.lang = lang;
        this.result = result;
        this.status = status;
        this.sessionId = sessionId;
    }

    public DialogFlowResponse() {
        super();
    }

    public Result getResult() {
        return result;
    }

    public String getId() {
        return id;
    }

    public String getLang() {
        return lang;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
