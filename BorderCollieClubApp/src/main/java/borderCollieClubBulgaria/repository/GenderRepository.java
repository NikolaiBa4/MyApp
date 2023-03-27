package borderCollieClubBulgaria.repository;

import borderCollieClubBulgaria.domain.entities.GenderEntity;
import borderCollieClubBulgaria.domain.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity,Long> {
}
