package Bot.DTO.MarvelDTO;

import Bot.DTO.MarvelDTO.Item.SeriesItems;

import java.util.List;

public class Series {
    private Long available;
    private Long returned;
    private String collectionURI;
    List<SeriesItems> items;

    public Series(Long available, Long returned, String collectionURI, List<SeriesItems> items){
        this.available = available;
        this.returned = returned;
        this.collectionURI = collectionURI;
        this.items = items;
    }

    public Series(){
        super();
    }

    public List<SeriesItems> getItems() {
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

    public void setItems(List<SeriesItems> items) {
        this.items = items;
    }

    public void setReturned(Long returned) {
        this.returned = returned;
    }
}
