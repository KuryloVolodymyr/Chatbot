package Bot.DTO.DialogFlowDTO;

public class Metadata {
    private String intentId;
    private String webhookUsed;
    private String webhookForSlotFillingUsed;
    private String intentName;

    public Metadata(String intentId, String webhookUsed, String webhookForSlotFillingUsed, String intentName){
        this.intentId = intentId;
        this.webhookUsed = webhookUsed;
        this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
        this.intentName = intentName;
    }

    public Metadata(){
        super();
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

    public void setIntentId(String intentId) {
        this.intentId = intentId;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public void setWebhookForSlotFillingUsed(String webhookForSlotFillingUsed) {
        this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
    }

    public void setWebhookUsed(String webhookUsed) {
        this.webhookUsed = webhookUsed;
    }
}
