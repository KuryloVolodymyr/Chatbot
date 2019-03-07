package bot.dto.MarvelDTO;

import java.util.List;

public class Events {
    private Long available;
    private Long returned;
    private String collectionURI;
    List<EventItems> items;

    public Events(Long available, Long returned, String collectionURI, List<EventItems> items){
        this.available = available;
        this.returned = returned;
        this.collectionURI = collectionURI;
        this.items = items;
    }

    public Events(){
        super();
    }

    public List<EventItems> getItems() {
        return items;
    }

    public Long getAvailable() {
        return available;
    }

    public Long getReturned() {
        return returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public void setItems(List<EventItems> items) {
        this.items = items;
    }

    public void setReturned(Long returned) {
        this.returned = returned;
    }
}
