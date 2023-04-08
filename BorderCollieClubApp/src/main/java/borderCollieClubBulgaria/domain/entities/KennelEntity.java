package borderCollieClubBulgaria.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kennels")
public class KennelEntity extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @ManyToOne
    CountryEntity countryEntity;

    @OneToMany(orphanRemoval = true, mappedBy = "kennel")
    List<DogEntity>dogs;


    public KennelEntity() {
    }

    public KennelEntity(String name, String email, CountryEntity countryEntity) {
        this.name = name;
        this.email = email;
        this.countryEntity = countryEntity;
        dogs=new ArrayList<>();
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public KennelEntity setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
        return this;
    }

    public List<DogEntity> getDogs() {
        return dogs;
    }

    public KennelEntity setDogs(List<DogEntity> dogs) {
        this.dogs = dogs;
        return this;
    }

    public String getName() {
        return name;
    }

    public KennelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public KennelEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public CountryEntity getCountry() {
        return countryEntity;
    }

    public KennelEntity setCountry(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
        return this;
    }
}
