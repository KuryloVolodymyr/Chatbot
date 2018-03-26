package Bot.DTO.RequestDTO;

public class Payload {
    String url;

    public Payload(String url){
        this.url = url;
    }

    public Payload(){
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
