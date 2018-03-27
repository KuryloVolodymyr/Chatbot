package Bot.Domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;

@Entity
@Table(name = "userRequest")
@EntityListeners(AuditingEntityListener.class)
public class UserRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String heroName;

    @Column
    private Long heroId;

    @Column
    private Long senderPSID;


    public UserRequestEntity( String heroName, Long heroId, Long senderPSID){
        this.heroId = heroId;
        this.heroName = heroName;
        this.senderPSID = senderPSID;
    }

    public UserRequestEntity(){}

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long heroId) {
        this.heroId = heroId;
    }

    public Long getSenderPSID() {
        return senderPSID;
    }

    public void setSenderPSID(Long senderPSID) {
        this.senderPSID = senderPSID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

