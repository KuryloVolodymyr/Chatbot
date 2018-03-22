package Bot.Domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "marvel")
@EntityListeners(AuditingEntityListener.class)
public class MessageEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String senderpsid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenderpsid() {
        return senderpsid;
    }

    public void setSenderpsid(String senderpsid) {
        this.senderpsid = senderpsid;
    }
}