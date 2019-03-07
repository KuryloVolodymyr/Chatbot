package bot.dto.Elements;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GenericPayload implements Payload {

    @JsonProperty("template_type")
    private String templateType;
    private List<GenericElement> elements;

    public GenericPayload(List<GenericElement> elements){
        this.templateType = "generic";
        this.elements = elements;
    }

    public GenericPayload(){
        super();
    }

    @Override
    public String getTemplateType() {
        return this.templateType;
    }

    @Override
    public void setTemplateType(String templateType) {
        this.templateType = templateType;

    }

    public List<GenericElement> getElements() {
        return elements;
    }

    public void setElements(List<GenericElement> elements) {
        this.elements = elements;
    }
}
