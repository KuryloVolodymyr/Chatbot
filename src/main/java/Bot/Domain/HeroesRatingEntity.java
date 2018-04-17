package Bot.Domain;

import javax.persistence.*;

@Entity
@Table(name = "heroesRating")
public class HeroesRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String heroName;

    @Column
    private Long senderPSID;

    @Column
    private Boolean rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public HeroesRatingEntity(String heroName, Long senderPSID, Boolean rating){
        this.heroName = heroName;
        this.senderPSID = senderPSID;
        this.rating = rating;
    }

    public HeroesRatingEntity(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Long getSenderPSID() {
        return senderPSID;
    }

    public void setSenderPSID(Long senderPSID) {
        this.senderPSID = senderPSID;
    }

    public Boolean getRating() {
        return rating;
    }

    public void setRating(Boolean rating) {
        this.rating = rating;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
