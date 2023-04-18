package borderCollieClubBulgaria.repository;

import borderCollieClubBulgaria.domain.entities.KennelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KennelRepository extends JpaRepository<KennelEntity, Long> {

    Optional<KennelEntity> findById(Long id);

    Optional<KennelEntity> findByName(String name);

    Optional<KennelEntity> findByEmail(String email);
}
