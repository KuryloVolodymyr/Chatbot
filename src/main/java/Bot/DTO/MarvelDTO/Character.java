package Bot.DTO.MarvelDTO;

import Bot.DTO.MarvelDTO.Item.CharacterItem;

import java.util.List;

public class Character {
     private Long available;
     private Long returned;
     private String collectionURI;
     private List<CharacterItem> items;

     public Character(Long available, Long returned, String collectionURI, List<CharacterItem> items){
         this.available = available;
         this.returned = returned;
         this.collectionURI = collectionURI;
         this.items = items;
     }
     public Character(){
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

    public List<CharacterItem> getItems() {
        return items;
    }

    public void setReturned(Long returned) {
        this.returned = returned;
    }

    public void setItems(List<CharacterItem> items) {
        this.items = items;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }
}
