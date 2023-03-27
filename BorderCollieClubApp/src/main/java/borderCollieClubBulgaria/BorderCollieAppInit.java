package borderCollieClubBulgaria;

import borderCollieClubBulgaria.domain.entities.*;
import borderCollieClubBulgaria.domain.enums.Gender;
import borderCollieClubBulgaria.domain.enums.UserRoles;
import borderCollieClubBulgaria.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BorderCollieAppInit implements CommandLineRunner {

    private final GenderRepository genderRepository;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final CountryRepository countryRepository;

    private final KennelRepository kennelRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    BorderCollieAppInit(GenderRepository genderRepository, RoleRepository roleRepository, UserRepository userRepository, CountryRepository countryRepository, KennelRepository kennelRepository, PasswordEncoder passwordEncoder) {
        this.genderRepository = genderRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
        this.kennelRepository = kennelRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.genderRepository.count() == 0 && roleRepository.count()==0) {
            gendersInit();
            rolesInit();
        }

        if(countryRepository.count()==0){
            countryInit();
        }

        if (kennelRepository.count()==0){
            kennelInit();
        }

    }

    private void gendersInit() {
        this.genderRepository.saveAllAndFlush(Arrays.stream(Gender.values())
                    .map(GenderEntity::new)
                    .toList());

    }

    private void rolesInit() {
        this.roleRepository.saveAllAndFlush(Arrays.stream(UserRoles.values())
                .map(RoleEntity::new)
                .toList());

    }

    private void countryInit(){

        List<String>countriesNames=List.of("Bulgaria","Romania");
        this.countryRepository.saveAllAndFlush(countriesNames.stream().map(CountryEntity::new).toList());
    }

    private void kennelInit(){

        List<CountryEntity>countries=this.countryRepository.findAll();

        KennelEntity riverSideForest=new KennelEntity
                ("River Side Forest","riversideforest@gmail.com",countries.get(0));

        KennelEntity wildSpeedAngels=new KennelEntity
                ("Wild Speed Angels","wildspeedangels@gmail.com",countries.get(1));

        this.kennelRepository.saveAndFlush(riverSideForest);
        this.kennelRepository.saveAndFlush(wildSpeedAngels);


    }

}
