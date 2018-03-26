package Bot.DTO.MarvelDTO;

public class Urls {
    private String type;
    private String url;

    public Urls(String type, String url){
        this.type = type;
        this.url = url;
    }

    public Urls(){
        super();
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
