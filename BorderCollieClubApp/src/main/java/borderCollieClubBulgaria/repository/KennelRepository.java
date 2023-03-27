package borderCollieClubBulgaria.repository;

import borderCollieClubBulgaria.domain.entities.KennelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KennelRepository extends JpaRepository<KennelEntity,Long> {
}
