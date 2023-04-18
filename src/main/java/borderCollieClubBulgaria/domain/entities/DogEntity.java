package borderCollieClubBulgaria.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "dogs")
public class DogEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String gender;

    @ManyToOne
    private UserEntity owner;

    @ManyToOne
    private KennelEntity kennel;


    public DogEntity() {
    }

    public DogEntity(String name, String gender, Integer age, UserEntity owner, KennelEntity kennel) {
        this.name = name;
        this.gender = gender;
        this.owner = owner;
        this.kennel = kennel;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public DogEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public DogEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public DogEntity setGender(String gender) {
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

    public KennelEntity getKennel() {
        return kennel;
    }

    public DogEntity setKennel(KennelEntity kennel) {
        this.kennel = kennel;
        return this;
    }

}
