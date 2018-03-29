package Bot.DTO.DialogFlowDTO.DialogFlowResponse;

public class Status {
    private Long code;
    private String errorType;
    private String errorDetails;
    private Boolean webhookTimedOut;

    public Status(Long code, String errorType,String errorDetails, Boolean webhookTimedOut){
        this.code = code;
        this.errorType = errorType;
        this.errorDetails = errorDetails;
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

    public String getErrorDetails() {
        return errorDetails;
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

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}
