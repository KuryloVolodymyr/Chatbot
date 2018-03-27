package Bot.DTO;

public class DialogFlowTest {
    private String speech;
    private String displayText;

    public DialogFlowTest(String speech, String displayText){
        this.speech = speech;
        this.displayText = displayText;
    }

    public DialogFlowTest(){
        super();
    }

    public String getDisplayText() {
        return displayText;
    }

    public String getSpeech() {
        return speech;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }
}
