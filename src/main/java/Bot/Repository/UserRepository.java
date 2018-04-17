package Bot.Repository;

import Bot.Domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity getBySenderPSID(Long senderpsid);

}
