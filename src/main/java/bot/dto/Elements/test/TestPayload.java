package bot.dto.Elements.test;

import bot.dto.Elements.GenericElement;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestPayload {
    private String type;
    private String text;
    private List<TestButton> buttons;
    private String url;
    @JsonProperty("template_type")
    private String templateType;
    private List<TestGenericElement> elements;




    public TestPayload(String text, List<TestButton> buttons) {
        this.type = "button";
        this.text = text;
        this.buttons = buttons;
    }

    public TestPayload(String type, String text, List<TestButton> buttons) {
        this.type = type;
        this.text = text;
        this.buttons = buttons;
    }

    public TestPayload(List<TestGenericElement> elements){
        this.templateType = "generic";
        this.elements = elements;
    }


    public String getTemplateType() {
        return type;
    }

    public TestPayload(String url) {
        this.url = url;
    }

    public TestPayload() {
    }

    public void setTemplateType(String templateType) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TestButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<TestButton> buttons) {
        this.buttons = buttons;
    }


    public List<TestGenericElement> getElements() {
        return elements;
    }

    public void setElements(List<TestGenericElement> elements) {
        this.elements = elements;
    }

}
