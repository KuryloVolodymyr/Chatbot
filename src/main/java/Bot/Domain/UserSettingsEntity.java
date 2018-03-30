package Bot.Domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "userSettings")
@EntityListeners(AuditingEntityListener.class)
public class UserSettingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long senderPSID;

    @Column
    private Long comicsGivenAtOnce;

    public UserSettingsEntity(Long senderPSID){
        this.senderPSID = senderPSID;
        this.comicsGivenAtOnce = 5L;
    }

    public UserSettingsEntity(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderPSID() {
        return senderPSID;
    }

    public void setSenderPSID(Long senderPSID) {
        this.senderPSID = senderPSID;
    }

    public Long getComicsGivenAtOnce() {
        return comicsGivenAtOnce;
    }

    public void setComicsGivenAtOnce(Long comicsGivenAtOnce) {
        this.comicsGivenAtOnce = comicsGivenAtOnce;
    }
}
