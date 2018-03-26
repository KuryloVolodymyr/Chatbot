package Bot.DTO.MarvelDTO;

public class Collections {
    private String resourceURI;
    private String name;

    public Collections(String resourceURI, String name){
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public Collections(){
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
