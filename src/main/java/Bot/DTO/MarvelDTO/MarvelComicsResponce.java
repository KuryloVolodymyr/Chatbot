package Bot.DTO.MarvelDTO;

public class MarvelComicsResponce {
    private Long code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private ComicsData data;
    private String  etag;

    public MarvelComicsResponce(Long code, String status, String copyright, String attributionText, String attributionHTML, ComicsData data, String etag){
        this.code = code;
        this.status = status;
        this.copyright = copyright;
        this.attributionText = attributionText;
        this.attributionHTML = attributionHTML;
        this.data = data;
        this.etag = etag;
    }

    public MarvelComicsResponce(){
        super();
    }

    public String getStatus() {
        return status;
    }

    public String getEtag() {
        return etag;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public ComicsData getData() {
        return data;
    }

    public Long getCode() {
        return code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public void setData(ComicsData data) {
        this.data = data;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
