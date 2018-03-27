package Bot.Repository;

import Bot.Domain.HeroesRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroesRatingRepository extends JpaRepository <HeroesRatingEntity, Long> {

    public HeroesRatingEntity getByHeroNameAndSenderPSID(String heroName, Long senderPSID);
}
