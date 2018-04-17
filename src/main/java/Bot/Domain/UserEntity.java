package Bot.Domain;

import Bot.DTO.UserProfile;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long senderPSID;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Long comicsGivenAtOnce;

    public UserEntity(Long senderPSID, UserProfile userProfile){
        this.senderPSID = senderPSID;
        this.firstName = userProfile.getFirstName();
        this.lastName = userProfile.getLastName();
        this.comicsGivenAtOnce = 5L;
    }

    public UserEntity(){
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
}
