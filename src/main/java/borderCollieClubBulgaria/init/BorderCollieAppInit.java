package borderCollieClubBulgaria.init;

import borderCollieClubBulgaria.domain.entities.*;
import borderCollieClubBulgaria.domain.enums.UserRoles;
import borderCollieClubBulgaria.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BorderCollieAppInit implements CommandLineRunner {

    private static final String BULGARIA = "Bulgaria";

    private static final String ROMANIA = "Romania";

    private static final String APRIL_NAME = "April From Wild Speed Angels";

    private static final String AHINORA_NAME = "Ahinora From River Side Forest";


    private static final String WILD_SPEED_ANGELS_NAME = "Wild Speed Angels";

    private static final String WILD_SPEED_ANGELS_EMAIL = "wildspeedangels@gmail.com";


    private static final String FEMALE_GENDER = "Female";

    private static final String RIVERSIDE_FOREST_NAME = "River Side Forest";

    private static final String RIVERSIDE_FOREST_EMAIL = "riversideforest@gmail.com";

    private static final String ADMIN_USERNAME = "nikolai_ba4";

    private static final String ADMIN_FIRST_NAME = "Nikolay";

    private static final String ADMIN_LAST_NAME = "Bachvarov";

    private static final String ADMIN_RAW_PASSWORD = "topsecret";

    private static final String ADMIN_EMAIL = "nikolai_ba4@abv.bg";

    private static final Integer ADMIN_AGE = 42;


    private static final String USER_USERNAME = "tanya_t";

    private static final String USER_FIRST_NAME = "Tanya";

    private static final String USER_LAST_NAME = "Tacheva";

    private static final String USER_RAW_PASSWORD = "topsecret";

    private static final String USER_EMAIL = "tanya_t@abv.bg";

    private static final Integer USER_AGE = 38;


    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final CountryRepository countryRepository;

    private final KennelRepository kennelRepository;

    private final DogRepository dogRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    BorderCollieAppInit(RoleRepository roleRepository, UserRepository userRepository,
                        CountryRepository countryRepository, KennelRepository kennelRepository,
                        DogRepository dogRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
        this.kennelRepository = kennelRepository;
        this.dogRepository = dogRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.roleRepository.count() == 0) {

            rolesInit();
        }

        if (this.countryRepository.count() == 0) {
            countryInit();
        }

        if (this.kennelRepository.count() == 0) {
            kennelInit();
        }

        if (this.userRepository.count() == 0) {
            initUsers();
        }

        if (this.dogRepository.count() == 0) {
            initDogs();
        }

    }


    private void rolesInit() {
        this.roleRepository.saveAllAndFlush(Arrays.stream(UserRoles.values())
                .map(RoleEntity::new)
                .toList());

    }

    private void countryInit() {

        List<String> countriesNames = List.of(BULGARIA, ROMANIA);
        this.countryRepository.saveAllAndFlush(countriesNames.stream().map(CountryEntity::new).toList());
    }

    private void kennelInit() {

        this.kennelRepository.saveAllAndFlush(List.of(initWildSpeedAngels(), initRiversideForest()));

    }


    private void initUsers() {

        this.userRepository.saveAllAndFlush(List.of(initAdmin(), initUser()));

    }


    private void initDogs() {

        this.dogRepository.saveAllAndFlush(List.of(initApril(), initAhinora()));

    }

    private DogEntity initApril() {
        return new DogEntity(APRIL_NAME, femaleGender(), 10, allUsers().get(1), allKennels().get(0));
    }

    private DogEntity initAhinora() {
        return new DogEntity(AHINORA_NAME, femaleGender(), 4, allUsers().get(0), allKennels().get(1));
    }

    private UserEntity initAdmin() {
        return new UserEntity(ADMIN_USERNAME,
                passwordEncoder.encode(ADMIN_RAW_PASSWORD),
                ADMIN_EMAIL,
                ADMIN_FIRST_NAME,
                ADMIN_LAST_NAME,
                ADMIN_AGE,
                getAllCountry().get(0), allRoles());
    }

    private UserEntity initUser() {
        return new UserEntity(USER_USERNAME,
                passwordEncoder.encode(USER_RAW_PASSWORD),
                USER_EMAIL,
                USER_FIRST_NAME,
                USER_LAST_NAME,
                USER_AGE,
                getAllCountry().get(0), List.of(allRoles().get(1)));
    }

    private List<RoleEntity> allRoles() {
        return this.roleRepository.findAll();
    }

    private KennelEntity initRiversideForest() {

        return new KennelEntity
                (RIVERSIDE_FOREST_NAME, RIVERSIDE_FOREST_EMAIL, getAllCountry().get(0));

    }

    private KennelEntity initWildSpeedAngels() {

        return new KennelEntity
                (WILD_SPEED_ANGELS_NAME, WILD_SPEED_ANGELS_EMAIL, getAllCountry().get(1));

    }

    private List<CountryEntity> getAllCountry() {

        return this.countryRepository.findAll();
    }

    private String femaleGender() {
        return FEMALE_GENDER;
    }

    private List<UserEntity> allUsers() {
        return this.userRepository.findAll();
    }

    private List<KennelEntity> allKennels() {
        return this.kennelRepository.findAll();
    }


}
