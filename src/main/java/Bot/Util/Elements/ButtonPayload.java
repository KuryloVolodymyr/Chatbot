package Bot.Util.Elements;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ButtonPayload implements Payload {
    @JsonProperty("template_type")
    private String templateType;
    private String text;
    private List<Button> buttons;

    public ButtonPayload() {
    }

    public ButtonPayload(String text, List<Button> buttons) {
        this.templateType = "button";
        this.text = text;
        this.buttons = buttons;
    }

    public ButtonPayload(String templateType, String text, List<Button> buttons) {
        this.templateType = templateType;
        this.text = text;
        this.buttons = buttons;
    }
    @Override
    public String getTemplateType() {
        return templateType;
    }

    @Override
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
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
