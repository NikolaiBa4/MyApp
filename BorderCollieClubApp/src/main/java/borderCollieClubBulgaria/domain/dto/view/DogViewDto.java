package borderCollieClubBulgaria.domain.dto.view;


public class DogViewDto {

    private Long id;

    private String name;

    private String gender;

    private Integer age;

    private String ownerName;

    private String kennelName;

    private String kennelCountry;


    public DogViewDto(Long id, String name,
                      String gender, Integer age,
                      String ownerName, String kennelName,
                      String kennelCountry) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.ownerName = ownerName;
        this.kennelName = kennelName;
        this.kennelCountry = kennelCountry;
    }

    public DogViewDto() {
    }


    public String getKennelCountry() {
        return kennelCountry;
    }

    public DogViewDto setKennelCountry(String kennelCountry) {
        this.kennelCountry = kennelCountry;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public DogViewDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public DogViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public DogViewDto setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public DogViewDto setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    public String getKennelName() {
        return kennelName;
    }

    public DogViewDto setKennelName(String kennelName) {
        this.kennelName = kennelName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public DogViewDto setId(Long id) {
        this.id = id;
        return this;
    }

}
