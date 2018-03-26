package Bot.DTO.Elements;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ButtonPayload implements Payload {
    private String type;
    private String text;
    private List<Button> buttons;

    public ButtonPayload() {
    }

    public ButtonPayload(String text, List<Button> buttons) {
        this.type = "button";
        this.text = text;
        this.buttons = buttons;
    }

    public ButtonPayload(String type, String text, List<Button> buttons) {
        this.type = type;
        this.text = text;
        this.buttons = buttons;
    }
    @Override
    public String getTemplateType() {
        return type;
    }

    @Override
    public void setTemplateType(String templateType) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }
}
