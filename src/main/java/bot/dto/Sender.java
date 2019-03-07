package bot.dto;

public class Sender  {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sender(Long id){
        this.id = id;
    }

    public Sender(){
        super();
    }

    @Override
    public String toString() {
        return "\"sender\":\n" +
                "\t\t\t\t\t{\"id\":\""+id+"\"},";
    }
}
