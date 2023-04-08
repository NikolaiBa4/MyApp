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

        List<String> countriesNames = List.of("Bulgaria", "Romania");
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
        return new DogEntity("April From Wild Speed Angels", femaleGender(), 10, allUsers().get(1), allKennels().get(0));
    }

    private DogEntity initAhinora() {
        return new DogEntity("Ahinora From River Side Forest", femaleGender(), 4, allUsers().get(0), allKennels().get(1));
    }

    private UserEntity initAdmin() {
        return new UserEntity("nikolai_ba4",
                passwordEncoder.encode("topsecret"),
                "nikolai_ba4@abv.bg",
                "Nikolai",
                "Bachvarov",
                42,
                getAllCountry().get(0), allRoles());
    }

    private UserEntity initUser() {
        return new UserEntity("tanya_t",
                passwordEncoder.encode("topsecret"),
                "tanya_t@abv.bg",
                "Tanya",
                "Tacheva",
                38,
                getAllCountry().get(0), List.of(allRoles().get(1)));
    }

    private List<RoleEntity> allRoles() {
        return this.roleRepository.findAll();
    }

    private KennelEntity initRiversideForest() {

        return new KennelEntity
                ("River Side Forest", "riversideforest@gmail.com", getAllCountry().get(0));

    }

    private KennelEntity initWildSpeedAngels() {

        return new KennelEntity
                ("Wild Speed Angels", "wildspeedangels@gmail.com", getAllCountry().get(1));

    }

    private List<CountryEntity> getAllCountry() {

        return this.countryRepository.findAll();
    }

    private String femaleGender() {
        return "Female";
    }

    private List<UserEntity> allUsers() {
        return this.userRepository.findAll();
    }

    private List<KennelEntity> allKennels() {
        return this.kennelRepository.findAll();
    }


}
