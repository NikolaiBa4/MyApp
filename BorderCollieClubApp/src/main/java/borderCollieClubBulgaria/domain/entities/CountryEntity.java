package borderCollieClubBulgaria.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
public class CountryEntity extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String name;

    public CountryEntity() {
    }


    public CountryEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CountryEntity setName(String name) {
        this.name = name;
        return this;
    }
}
