package borderCollieClubBulgaria.domain.dto.binding;


import borderCollieClubBulgaria.web.annotations.userAnnotations.checkEmailExistence.CheckEmailExistence;
import borderCollieClubBulgaria.web.annotations.userAnnotations.checkUserExsitence.CheckUserExistence;
import borderCollieClubBulgaria.web.annotations.userAnnotations.passwordMatcher.PasswordMatch;
import jakarta.validation.constraints.*;

@PasswordMatch(password = "password", confirmPassword = "confirmPassword")
@CheckUserExistence(username = "username")
@CheckEmailExistence(email = "email")
public class UserRegisterFormDto {

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

    public String getEmail() {
        return email;
    }

    public UserRegisterFormDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterFormDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterFormDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterFormDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterFormDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;

    }

    public String getUsername() {
        return username;
    }

    public UserRegisterFormDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserRegisterFormDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public UserRegisterFormDto setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }
}