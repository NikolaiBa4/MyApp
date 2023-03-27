package borderCollieClubBulgaria.repository;

import borderCollieClubBulgaria.domain.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity,Long> {


    Optional<CountryEntity> findByName(String name);
}
