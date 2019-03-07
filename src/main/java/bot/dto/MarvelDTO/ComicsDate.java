package bot.dto.MarvelDTO;

public class ComicsDate {
    private String type;
    private String date;

    public ComicsDate(String type, String date){
        this.type = type;
        this.date = date;
    }

    public ComicsDate(){
        super();
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
