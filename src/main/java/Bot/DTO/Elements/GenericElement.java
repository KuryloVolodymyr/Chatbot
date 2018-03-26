package Bot.DTO.Elements;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GenericElement {
    private String title;
    private String subtitle;
    @JsonProperty("image_url")
    private String imageULR;
    private List<Button> buttons;

    public GenericElement(String title, String subtitle, String imageULR, List<Button> buttons){
        this.title = title;
        this.subtitle = subtitle;
        this.imageULR = imageULR;
        this.buttons = buttons;
    }

    public GenericElement(String title, String subtitle, String imageULR){
        this.title = title;
        this.subtitle = subtitle;
        this.imageULR = imageULR;
    }

    public GenericElement(){
        super();
    }

    public String getTitle() {
        return title;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public String getImageULR() {
        return imageULR;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public void setImageULR(String imageULR) {
        this.imageULR = imageULR;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
