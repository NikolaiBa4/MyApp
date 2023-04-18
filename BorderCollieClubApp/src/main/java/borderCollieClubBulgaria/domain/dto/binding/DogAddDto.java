package borderCollieClubBulgaria.domain.dto.binding;


import borderCollieClubBulgaria.web.annotations.dogAnnotation.CheckDogExistence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@CheckDogExistence(name = "name")
public class DogAddDto {

    @NotNull
    @Size(min = 3, max = 80)
    private String name;

    @NotNull
    private String gender;

    @NotNull
    private Integer age;

    @NotNull
    @Size(min = 5, max = 80)
    private String kennelName;

    @NotNull
    @Size(min = 3, max = 20)
    private String kennelCountry;

    @NotBlank
    @Email
    private String kennelEmail;

    public DogAddDto() {
    }

    public DogAddDto(String name, String gender, Integer age, String kennelName, String kennelCountry, String kennelEmail) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.kennelName = kennelName;
        this.kennelCountry = kennelCountry;
        this.kennelEmail = kennelEmail;
    }

    public Integer getAge() {
        return age;
    }

    public DogAddDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getKennelCountry() {
        return kennelCountry;
    }

    public DogAddDto setKennelCountry(String kennelCountry) {
        this.kennelCountry = kennelCountry;
        return this;
    }

    public String getKennelEmail() {
        return kennelEmail;
    }

    public DogAddDto setKennelEmail(String kennelEmail) {
        this.kennelEmail = kennelEmail;
        return this;
    }

    public String getName() {
        return name;
    }

    public DogAddDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public DogAddDto setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getKennelName() {
        return kennelName;
    }

    public DogAddDto setKennelName(String kennelName) {
        this.kennelName = kennelName;
        return this;
    }
}
