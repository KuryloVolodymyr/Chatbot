package Bot.DTO.MarvelDTO;

import Bot.DTO.MarvelDTO.Item.Items;

public class StoryItem implements Items {
    private String resourceURI;
    private String name;
    private String type;

    public StoryItem(String resourceURI, String name, String type){
        this.resourceURI = resourceURI;
        this.name = name;
        this.type = type;
    }

    public StoryItem(){
        super();
    }

    public String getName() {
        return name;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }
}
