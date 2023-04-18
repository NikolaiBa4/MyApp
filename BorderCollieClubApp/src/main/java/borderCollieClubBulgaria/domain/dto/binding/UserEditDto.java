package borderCollieClubBulgaria.domain.dto.binding;

import borderCollieClubBulgaria.web.annotations.userAnnotations.passwordMatcher.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@PasswordMatch(password = "password"
        ,confirmPassword = "confirmPassword")
public class UserEditDto {

    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Email
    private String email;


    @NotNull
    private Integer age;

    @NotNull
    @Size(min = 5, max = 20)
    private String password;

    @NotNull
    @Size(min = 5, max = 20)
    private String confirmPassword;

    @NotNull
    @Size(min = 3, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 20)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 20)
    private String countryName;

    public UserEditDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserEditDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEditDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserEditDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEditDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserEditDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEditDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEditDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public UserEditDto setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserEditDto setId(Long id) {
        this.id = id;
        return this;
    }
}
