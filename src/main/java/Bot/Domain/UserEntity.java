package Bot.Domain;

import Bot.DTO.UserProfile;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column
    private Long senderPSID;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Long comicsGivenAtOnce;

//    @OneToMany(cascade = CascadeType.ALL,
//            mappedBy = "user")
//    private List<UserRequestEntity> requests = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL,
//            mappedBy = "user")
//    private List<HeroesRatingEntity> ratings = new ArrayList<>();

    public UserEntity(Long senderPSID, UserProfile userProfile){
        this.senderPSID = senderPSID;
        this.firstName = userProfile.getFirstName();
        this.lastName = userProfile.getLastName();
        this.comicsGivenAtOnce = 5L;
    }

    public UserEntity(){
        super();
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

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//    public List<UserRequestEntity> getRequests() {
//        return requests;
//    }
//
//    public void setRequests(List<UserRequestEntity> requests) {
//        this.requests = requests;
//    }
}
