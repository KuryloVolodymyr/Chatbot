package bot.dto.MarvelDTO;

import java.util.List;

public class Stories {
    private Long available;
    private Long returned;
    private String collectionURI;
    List<StoryItem> items;

    public Stories(Long available, Long returned, String collectionURI, List<StoryItem> items){
        this.available = available;
        this.returned = returned;
        this.collectionURI = collectionURI;
        this.items = items;
    }

    public Stories(){
        super();
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public Long getReturned() {
        return returned;
    }

    public Long getAvailable() {
        return available;
    }

    public List<StoryItem> getItems() {
        return items;
    }

    public void setReturned(Long returned) {
        this.returned = returned;
    }

    public void setItems(List<StoryItem> items) {
        this.items = items;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }
}
