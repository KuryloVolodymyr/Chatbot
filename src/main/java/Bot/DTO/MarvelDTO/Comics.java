package Bot.DTO.MarvelDTO;

import Bot.DTO.MarvelDTO.Item.ComicsItem;

import java.util.List;

public class Comics {
    private Long available;
    private Long returned;
    private String collectionURI;
    List<ComicsItem> items;

    public Comics(Long available, Long returned, String collectionURI, List<ComicsItem> items){
        this.available = available;
        this.returned = returned;
        this.collectionURI = collectionURI;
        this.items = items;
    }

    public Comics(){
        super();
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

    public List<ComicsItem> getItems() {
        return items;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public void setItems(List<ComicsItem> items) {
        this.items = items;
    }

    public void setReturned(Long returned) {
        this.returned = returned;
    }
}
