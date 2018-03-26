package Bot.DTO.MarvelDTO;

public class ComicsSeries {
    private String resourceURI;
    private String name;

    public ComicsSeries(String resourceURI, String name){
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public ComicsSeries(){
        super();
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public void setName(String name) {
        this.name = name;
    }
}
