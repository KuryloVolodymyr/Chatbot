package Bot.DTO.MarvelDTO;

public class Price {
    private String type;
    private Float price;

    public Price(String type, Float price){
        this.type = type;
        this.price = price;
    }

    public Price(){
        super();
    }

    public String getType() {
        return type;
    }

    public Float getPrice() {
        return price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
