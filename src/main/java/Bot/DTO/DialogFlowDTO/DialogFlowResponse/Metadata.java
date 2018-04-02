package Bot.DTO.DialogFlowDTO.DialogFlowResponse;

public class Metadata {
    private String intentId;
    private String webhookUsed;
    private String webhookForSlotFillingUsed;
    private Long webhookResponseTime;
    private String intentName;
    private Boolean empty;

    public Metadata(String intentId, String webhookUsed, String webhookForSlotFillingUsed, Long webhookResponseTime, String intentName){
        this.intentId = intentId;
        this.webhookUsed = webhookUsed;
        this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
        this.webhookResponseTime = webhookResponseTime;
        this.intentName = intentName;
        this.empty = false;
    }

    public Metadata(){
        this.empty = true;
    }

    public String getIntentId() {
        return intentId;
    }

    public String getIntentName() {
        return intentName;
    }

    public String getWebhookForSlotFillingUsed() {
        return webhookForSlotFillingUsed;
    }

    public String getWebhookUsed() {
        return webhookUsed;
    }

    public Long getWebhookResponseTime() {
        return webhookResponseTime;
    }

    public void setIntentId(String intentId) {
        this.intentId = intentId;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public void setWebhookForSlotFillingUsed(String webhookForSlotFillingUsed) {
        this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
    }

    public Boolean isEmpty(){
        return empty;
    }


    public void setWebhookUsed(String webhookUsed) {
        this.webhookUsed = webhookUsed;
    }

    public void setWebhookResponseTime(Long webhookResponseTime) {
        this.webhookResponseTime = webhookResponseTime;
    }
}
