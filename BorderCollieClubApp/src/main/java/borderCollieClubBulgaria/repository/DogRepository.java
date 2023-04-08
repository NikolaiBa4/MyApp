package borderCollieClubBulgaria.repository;

import borderCollieClubBulgaria.domain.entities.DogEntity;
import borderCollieClubBulgaria.domain.entities.KennelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<DogEntity, Long> {

    Optional<DogEntity> findByName(String name);

}
