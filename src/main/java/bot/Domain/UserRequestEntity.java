package bot.domain;

import javax.persistence.*;

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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "senderId", nullable = false)
//    private UserEntity user;


    public UserRequestEntity( String heroName, Long heroId){
        this.heroId = heroId;
        this.heroName = heroName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public UserEntity getUser() {
//        return user;
//    }
//
//    public void setUser(UserEntity user) {
//        this.user = user;
//    }
}

