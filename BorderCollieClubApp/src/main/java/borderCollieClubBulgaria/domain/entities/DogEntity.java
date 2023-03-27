package borderCollieClubBulgaria.domain.entities;

import borderCollieClubBulgaria.domain.enums.Gender;
import borderCollieClubBulgaria.domain.enums.UserRoles;
import jakarta.persistence.*;

@Entity
@Table(name = "dogs")
public class DogEntity extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToOne
    private GenderEntity gender;

    @ManyToOne
    private UserEntity owner;

    public DogEntity() {
    }

    public String getName() {
        return name;
    }

    public DogEntity setName(String name) {
        this.name = name;
        return this;
    }

    public GenderEntity getGender() {
        return gender;
    }

    public DogEntity setGender(GenderEntity gender) {
        this.gender = gender;
        return this;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public DogEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }
}
