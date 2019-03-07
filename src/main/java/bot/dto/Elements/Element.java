package bot.dto.Elements;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Element {
    private String title;
    @JsonProperty("item_url")
    private String itemUrl;
    @JsonProperty("image_url")
    private String imageUrl;
    private String subtitle;
    private List<Button> buttons;

    public Element() {
    }

    public Element(String title, String itemUrl, String imageUrl, String subtitle, List<Button> buttons) {
        this.title = title;
        this.itemUrl = itemUrl;
        this.imageUrl = imageUrl;
        this.subtitle = subtitle;
        this.buttons = buttons;
    }

    public Element(String title, String imageUrl, String subtitle, List<Button> buttons) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.subtitle = subtitle;
        this.buttons = buttons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }
}
