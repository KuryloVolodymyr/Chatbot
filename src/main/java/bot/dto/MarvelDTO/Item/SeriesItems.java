package bot.dto.MarvelDTO.Item;

public class SeriesItems implements Items {
    private String resourceURI;
    private String name;

    public SeriesItems(String resourceURI, String name){
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public SeriesItems(){
        super();
    }

    public String getName() {
        return name;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }
}
