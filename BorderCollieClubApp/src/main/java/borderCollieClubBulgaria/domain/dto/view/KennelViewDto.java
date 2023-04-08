package borderCollieClubBulgaria.domain.dto.view;

public class KennelViewDto {

    private Long id;

    private String name;

    private String email;

    private String countryName;

    public KennelViewDto(Long id, String name, String email, String countryName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.countryName = countryName;
    }

    public KennelViewDto() {
    }

    public String getName() {
        return name;
    }

    public KennelViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public KennelViewDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public KennelViewDto setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public KennelViewDto setId(Long id) {
        this.id = id;
        return this;
    }
}
