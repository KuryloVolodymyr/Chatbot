package bot.dto.Broadcast;

public class DynamicText {
    private String text;
    private String fallback_text;

    public DynamicText(String text, String fallback_text){
        this.text = text;
        this.fallback_text = fallback_text;
    }

    public DynamicText(){

    }

    public String getText() {
        return text;
    }

    public String getFallback_text() {
        return fallback_text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFallback_text(String fallback_text) {
        this.fallback_text = fallback_text;
    }
}
