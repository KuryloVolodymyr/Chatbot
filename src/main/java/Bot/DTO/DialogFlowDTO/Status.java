package Bot.DTO.DialogFlowDTO;

public class Status {
    private Long code;
    private String errorType;
    private Boolean webhookTimedOut;

    public Status(Long code, String errorType, Boolean webhookTimedOut){
        this.code = code;
        this.errorType = errorType;
        this.webhookTimedOut = webhookTimedOut;
    }

    public Status(){
        super();
    }

    public Long getCode() {
        return code;
    }

    public Boolean getWebhookTimedOut() {
        return webhookTimedOut;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setWebhookTimedOut(Boolean webhookTimedOut) {
        this.webhookTimedOut = webhookTimedOut;
    }
}
