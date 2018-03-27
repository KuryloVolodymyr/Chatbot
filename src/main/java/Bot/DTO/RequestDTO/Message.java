package Bot.DTO.RequestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Message {
    private String mid;
    private long seq;
    private String text;
    @JsonProperty("quick_reply")
    private QuickReply quickReply;
    private List<Attachments> attachments;

    public Message(String mid, long seq, String text){
        this.mid = mid;
        this.seq = seq;
        this.text = text;
    }

    public Message(String mid, long seq, String text, QuickReply quickReply){
        this.mid = mid;
        this.seq = seq;
        this.text = text;
        this.quickReply = quickReply;
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

    public QuickReply getQuickReply() {
        return quickReply;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public void setQuickReply(QuickReply quickReply) {
        this.quickReply = quickReply;
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
