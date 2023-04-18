package borderCollieClubBulgaria.domain.entities;

import borderCollieClubBulgaria.domain.enums.UserRoles;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private UserRoles name;

    public RoleEntity() {
    }

    public RoleEntity(UserRoles name) {
        this.name = name;
    }

    public UserRoles getName() {
        return name;
    }

    public RoleEntity setName(UserRoles name) {
        this.name = name;
        return this;
    }
}
