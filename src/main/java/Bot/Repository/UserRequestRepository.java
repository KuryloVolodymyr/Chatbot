package Bot.Repository;

import Bot.Domain.UserRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRepository extends JpaRepository<UserRequestEntity, Long> {
}
