package borderCollieClubBulgaria.domain.dto.binding;

import borderCollieClubBulgaria.web.annotations.kennelAnnotations.checkKennelEmailExistence.KennelEmailExistence;
import borderCollieClubBulgaria.web.annotations.kennelAnnotations.checkKennelExistence.KennelNameExistence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@KennelNameExistence(name = "name")
@KennelEmailExistence(email = "email")
public class KennelAddDto {

    @NotNull
    @Size(min = 5, max = 80)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 3, max = 20)
    private String countryName;

    public KennelAddDto(String name, String email, String countryName) {
        this.name = name;
        this.email = email;
        this.countryName = countryName;
    }

    public KennelAddDto() {
    }

    public String getName() {
        return name;
    }

    public KennelAddDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public KennelAddDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public KennelAddDto setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }
}
