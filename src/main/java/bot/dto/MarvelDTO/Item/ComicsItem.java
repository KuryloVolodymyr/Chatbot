package bot.dto.MarvelDTO.Item;

public class ComicsItem implements Items {
    private String resourceURI;
    private String name;

    public ComicsItem(String resourceURI, String name){
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public ComicsItem(){
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
