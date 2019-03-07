package bot.dto.MarvelDTO;

import bot.dto.MarvelDTO.Item.CreatorItem;

import java.util.List;

public class Creator {
    private Long available;
    private Long returned;
    private String collectionURI;
    private List<CreatorItem> items;

    public Creator(Long available, Long returned, String collectionURI, List<CreatorItem> items){
        this.available = available;
        this.returned = returned;
        this.collectionURI = collectionURI;
        this.items = items;
    }
    public Creator(){
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

    public List<CreatorItem> getItems() {
        return items;
    }

    public void setReturned(Long returned) {
        this.returned = returned;
    }

    public void setItems(List<CreatorItem> items) {
        this.items = items;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }
}
