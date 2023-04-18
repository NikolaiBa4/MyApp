package borderCollieClubBulgaria.domain.dto.view;

public class UserViewDto {

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private int age;

    private String country;

    public UserViewDto(Long id, String username, String email, String firstName, String lastName, int age, String country) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
    }

    public UserViewDto() {
    }

    public Long getId() {
        return id;
    }

    public UserViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserViewDto setAge(int age) {
        this.age = age;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserViewDto setCountry(String country) {
        this.country = country;
        return this;
    }
}
