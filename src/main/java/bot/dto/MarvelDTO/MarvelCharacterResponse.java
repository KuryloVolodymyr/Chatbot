package bot.dto.MarvelDTO;


public class MarvelCharacterResponse {
    private Long id;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private Data data;
    private String etag;

    public MarvelCharacterResponse(Long id, String status, String copyright, String attributionText, String attributionHTML, Data data, String etag){
        this.id = id;
        this.status = status;
        this.copyright = copyright;
        this.attributionText = attributionText;
        this.attributionHTML = attributionHTML;
        this.data = data;
        this.etag = etag;
    }

    public MarvelCharacterResponse(){
        super();
    }

    public Long getId() {
        return id;
    }

    public Data getData() {
        return data;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getEtag() {
        return etag;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
