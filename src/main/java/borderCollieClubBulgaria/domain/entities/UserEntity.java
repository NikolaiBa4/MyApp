package borderCollieClubBulgaria.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private int age;

    @ManyToOne
    private CountryEntity countryEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roles;

    @OneToMany(orphanRemoval = true, mappedBy = "owner")
    List<DogEntity> userDogs;


    public UserEntity() {
        this.roles = new ArrayList<>();
    }

    public UserEntity(String username, String password, String email, String firstName, String lastName
            , int age, CountryEntity countryEntity, List<RoleEntity> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.countryEntity = countryEntity;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public UserEntity setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
        return this;
    }

    public List<DogEntity> getUserDogs() {
        return userDogs;
    }

    public UserEntity setUserDogs(List<DogEntity> userDogs) {
        this.userDogs = userDogs;
        return this;
    }
}
