package Bot.DTO.MarvelDTO.Item;

public class CharacterItem implements Items {
    private String resourceURI;
    private String name;
    private String role;

    public CharacterItem(String resourceURI, String name, String role){
        this.resourceURI = resourceURI;
        this.name = name;
        this.role = role;
    }

    public CharacterItem(){
        super();
    }

    public String getName() {
        return name;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
