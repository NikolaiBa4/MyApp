package borderCollieClubBulgaria.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "kennels")
public class KennelEntity extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @ManyToOne
    CountryEntity countryEntity;

    public KennelEntity() {
    }

    public KennelEntity(String name, String email, CountryEntity countryEntity) {
        this.name = name;
        this.email = email;
        this.countryEntity = countryEntity;
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
