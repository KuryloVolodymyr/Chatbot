package bot.dto.MarvelDTO;

public class CollectedIssues {
    private String resourceURI;
    private String name;

    public CollectedIssues(String resourceURI, String name){
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public CollectedIssues(){
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
