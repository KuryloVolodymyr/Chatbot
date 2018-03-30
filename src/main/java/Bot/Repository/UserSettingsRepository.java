package Bot.Repository;

import Bot.Domain.UserSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettingsEntity, Long> {

    public UserSettingsEntity getBySenderPSID(Long senderpsid);

}
