package Bot.Util.Elements;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GenericPayload implements Payload {
    @JsonProperty("template_type")
    private String templateType;

    private List<Element> elements;

    public GenericPayload() {
    }

    public GenericPayload(List<Element> elements) {
        this.templateType = "generic";
        this.elements = elements;
    }

    public GenericPayload(String templateType, List<Element> elements) {
        this.templateType = templateType;
        this.elements = elements;
    }

    @Override
    public String getTemplateType() {
        return templateType;
    }

    @Override
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
