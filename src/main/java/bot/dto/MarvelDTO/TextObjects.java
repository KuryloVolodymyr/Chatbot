package bot.dto.MarvelDTO;

public class TextObjects {
    private String type;
    private String language;
    private String text;

    public TextObjects(String type, String language, String text){
        this.type = type;
        this.language = language;
        this.text = text;
    }

    public TextObjects(){
        super();
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getLanguage() {
        return language;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
