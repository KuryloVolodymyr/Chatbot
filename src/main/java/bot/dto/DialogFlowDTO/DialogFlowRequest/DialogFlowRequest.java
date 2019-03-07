package bot.dto.DialogFlowDTO.DialogFlowRequest;

public class DialogFlowRequest {
    private String lang;
    private String query;
    private String sessionId;
    private String timezone;

    public DialogFlowRequest(String lang, String query, String sessionId, String timezone) {
        this.lang = lang;
        this.query = query;
        this.sessionId = sessionId;
        this.timezone = timezone;
    }

    public DialogFlowRequest(String query) {
        this.lang = "en";
        this.query = query;
        this.sessionId = "12345";
        this.timezone = "Europe/Moscow";
    }

    public DialogFlowRequest() {
        super();
    }

    public String getQuery() {
        return query;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getLang() {
        return lang;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
