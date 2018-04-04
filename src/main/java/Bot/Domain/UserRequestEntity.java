package Bot.Domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "userRequest")
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

    @Column
    private LocalDateTime date;


    public UserRequestEntity( String heroName, Long heroId, Long senderPSID){
        this.heroId = heroId;
        this.heroName = heroName;
        this.senderPSID = senderPSID;
        this.date = LocalDateTime.now();
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

