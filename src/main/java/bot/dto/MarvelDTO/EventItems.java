package bot.dto.MarvelDTO;

import bot.dto.MarvelDTO.Item.Items;

public class EventItems implements Items {
    private String resourceURI;
    private String name;

    public EventItems(String resourceURI, String name){
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public EventItems(){
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
