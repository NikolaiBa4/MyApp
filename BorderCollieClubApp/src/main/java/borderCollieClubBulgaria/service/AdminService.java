package borderCollieClubBulgaria.service;

import borderCollieClubBulgaria.domain.dto.view.UserViewDto;
import borderCollieClubBulgaria.domain.entities.UserEntity;
import borderCollieClubBulgaria.domain.enums.UserRoles;
import borderCollieClubBulgaria.exception.ObjectNotFoundException;
import borderCollieClubBulgaria.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserViewDto> findAllUsers() {

        return this.userRepository.findAll().stream().map(this::maptoUserViewDto).toList();

    }

    public void deleteUserById(Long id) {

        this.userRepository.findById(id).
                ifPresent(userRepository::delete);
    }

    public UserViewDto findUserById(Long id) {
        return this.userRepository.findById(id).map(this::maptoUserViewDto).
                orElseThrow(() -> new ObjectNotFoundException("User with ID " + id + " not found"));
    }

    public UserEntity findUserByUsername(String username) {
        return this.userRepository.findByUsername(username).
                orElseThrow(() -> new ObjectNotFoundException("User with username " + username + " not found"));
    }

    public boolean isUserAdmin(UserDetails userDetails) {

        return userDetails.getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                anyMatch(a -> a.equals("ROLE_" + UserRoles.ADMIN.name()));
    }

    public UserViewDto maptoUserViewDto(UserEntity user) {

        return new UserViewDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setAge(user.getAge())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setCountry(user.getCountryEntity().getName());
    }

}
