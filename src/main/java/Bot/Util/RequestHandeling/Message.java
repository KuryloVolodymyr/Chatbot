package Bot.Util.RequestHandeling;

import java.util.List;

public class Message {
    String mid;
    long seq;
    String text;
    List<Attachments> attachments;

    public Message(String mid, long seq, String text){
        this.mid = mid;
        this.seq = seq;
        this.text = text;
    }

    public Message(String mid, long seq, List<Attachments> attachments){
        this.mid = mid;
        this.seq = seq;
        this.attachments = attachments;
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

    public List<Attachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachments> attachments) {
        this.attachments = attachments;
    }
}
