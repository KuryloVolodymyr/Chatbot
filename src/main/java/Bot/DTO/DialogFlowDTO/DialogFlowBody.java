package Bot.DTO.DialogFlowDTO;

import Bot.DTO.RequestDTO.Messaging;
import Bot.DTO.RequestDTO.RequestHandler;

public class DialogFlowBody {
    private OriginalRequest originalRequest;
    private String id;
    private String timestamp;
    private String lang;
    private Result result;
    private String sessionId;

    public DialogFlowBody(OriginalRequest originalRequest ,String id, String timestamp, String lang, Result result, String sessionId) {
        this.originalRequest = originalRequest;
        this.id = id;
        this.timestamp = timestamp;
        this.lang = lang;
        this.result = result;
        this.sessionId = sessionId;
    }

    public DialogFlowBody() {
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

    public OriginalRequest getOriginalRequest() {
        return originalRequest;
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

    public void setOriginalRequest(OriginalRequest originalRequest) {
        this.originalRequest = originalRequest;
    }
}
