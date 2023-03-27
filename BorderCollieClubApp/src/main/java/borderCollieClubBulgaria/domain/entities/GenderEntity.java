package borderCollieClubBulgaria.domain.entities;

import borderCollieClubBulgaria.domain.enums.Gender;
import jakarta.persistence.*;

@Entity
@Table(name = "genders")
public class GenderEntity extends BaseEntity {


    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Gender name;

    public GenderEntity(Gender name) {
        this.name = name;
    }

    public GenderEntity() {
    }

    public Gender getName() {
        return name;
    }

    public GenderEntity setName(Gender name) {
        this.name = name;
        return this;
    }
}
