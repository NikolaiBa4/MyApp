package borderCollieClubBulgaria.service;

import borderCollieClubBulgaria.domain.dto.binding.UserRegisterFormDto;
import borderCollieClubBulgaria.domain.entities.CountryEntity;
import borderCollieClubBulgaria.domain.entities.RoleEntity;
import borderCollieClubBulgaria.domain.entities.UserEntity;
import borderCollieClubBulgaria.exception.ObjectNotFoundException;
import borderCollieClubBulgaria.repository.CountryRepository;
import borderCollieClubBulgaria.repository.RoleRepository;
import borderCollieClubBulgaria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CountryRepository countryRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       CountryRepository countryRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.countryRepository = countryRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public void increaseAgeOfUsers() {

        List<UserEntity> users = this.userRepository.findAll();

        for (UserEntity user : users) {
            user.setAge(user.getAge() + 1);
            this.userRepository.saveAndFlush(user);
        }
    }

    public void registerUser(UserRegisterFormDto registrationDTO) {

        userRepository.save(mapToEntity(registrationDTO));

    }

    public UserEntity findByEmail(String email) {

        return this.userRepository.findByEmail(email).orElse(new UserEntity());
    }

    public UserEntity mapToEntity(UserRegisterFormDto registrationDTO) {

        return new UserEntity().
                setUsername(registrationDTO.getUsername()).
                setFirstName(registrationDTO.getFirstName()).
                setLastName(registrationDTO.getLastName()).
                setEmail(registrationDTO.getEmail()).
                setAge(registrationDTO.getAge()).
                setRoles(List.of(userRole())).
                setCountryEntity(country(registrationDTO.getCountryName())).
                setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
    }

    public RoleEntity userRole() {
        return this.roleRepository.findById(2L).
                orElseThrow(() -> new ObjectNotFoundException("This role not found"));
    }

    private CountryEntity country(String countryName) {

        if (this.countryRepository.findByName(countryName).isPresent()) {
            return this.countryRepository.findByName(countryName).
                    orElseThrow(() -> new ObjectNotFoundException("Country with name " + countryName + " not found"));
        }
        return this.countryRepository.saveAndFlush(new CountryEntity(countryName));

    }

    public UserEntity findByUsername(String userName) {
        return this.userRepository.findByUsername(userName).orElse(new UserEntity());
    }

}