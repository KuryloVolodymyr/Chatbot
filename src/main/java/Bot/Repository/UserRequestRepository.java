package Bot.Repository;

import Bot.Domain.UserRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequestEntity, Long> {
}
