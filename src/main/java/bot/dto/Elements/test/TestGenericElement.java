package bot.dto.Elements.test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestGenericElement {

    private String title;
    private String subtitle;
    @JsonProperty("image_url")
    private String imageULR;
    private List<TestButton> buttons;

    public TestGenericElement(String title, String subtitle, String imageULR, List<TestButton> buttons){
        this.title = title;
        this.subtitle = subtitle;
        this.imageULR = imageULR;
        this.buttons = buttons;
    }

    public TestGenericElement(String title, String subtitle, String imageULR){
        this.title = title;
        this.subtitle = subtitle;
        this.imageULR = imageULR;
    }

    public TestGenericElement(){
        super();
    }

    public String getTitle() {
        return title;
    }

    public List<TestButton> getButtons() {
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

    public void setButtons(List<TestButton> buttons) {
        this.buttons = buttons;
    }

    public void setImageULR(String imageULR) {
        this.imageULR = imageULR;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


}
