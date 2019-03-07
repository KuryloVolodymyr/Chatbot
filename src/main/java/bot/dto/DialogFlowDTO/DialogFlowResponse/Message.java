package bot.dto.DialogFlowDTO.DialogFlowResponse;

public class Message {
    private Long type;
    private String speech;

    public Message(Long type, String speech){
        this.type = type;
        this.speech = speech;
    }

    public Message(){
        super();
    }

    public String getSpeech() {
        return speech;
    }

    public Long getType() {
        return type;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
