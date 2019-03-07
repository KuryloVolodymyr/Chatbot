package bot.dto;

public class Recipient {
    private long id;

    public Recipient() {
    }

    public Recipient(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\"recepient\":\n" +
                "\t\t\t\t\t{\"id\":\""+id+"\"},";
    }
}
