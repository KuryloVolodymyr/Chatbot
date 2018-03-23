package Bot.Util.RequestHandeling;

public class Message {
    String mid;
    long seq;
    String text;

    public Message(String mid, long seq, String text){
        this.mid = mid;
        this.seq = seq;
        this.text = text;
    }

    public Message(){
        super();
    }

    public Long getSeq() {
        return seq;
    }

    public String getMid() {
        return mid;
    }

    public String getText() {
        return text;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public void setText(String text) {
        this.text = text;
    }
}
