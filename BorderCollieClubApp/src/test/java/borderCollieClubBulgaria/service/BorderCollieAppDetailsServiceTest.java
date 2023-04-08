package borderCollieClubBulgaria.service;

import borderCollieClubBulgaria.domain.entities.RoleEntity;
import borderCollieClubBulgaria.domain.entities.UserEntity;
import borderCollieClubBulgaria.domain.enums.UserRoles;
import borderCollieClubBulgaria.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BorderCollieAppDetailsServiceTest {


    private final String NON_EXISTING_USERNAME = "pesho@example.com";
    private BorderCollieAppDetailsService testDetails;


    @Mock
    private UserRepository mockUserRepository;


    @BeforeEach
    void setUp() {
        testDetails = new BorderCollieAppDetailsService
                (mockUserRepository);
    }

    @Test
    void testUserFound() {

        // ARRANGE
        RoleEntity testAdminRole = new RoleEntity().setName(UserRoles.ADMIN);
        RoleEntity testUserRole = new RoleEntity().setName(UserRoles.USER);

        String EXISTING_USERNAME = "tanya_t";
        UserEntity testUserEntity = new UserEntity().
                setUsername(EXISTING_USERNAME).
                setPassword("testPassword").
                setRoles(List.of(testAdminRole, testUserRole));


        when(mockUserRepository.findByUsername(EXISTING_USERNAME)).
                thenReturn(Optional.of(testUserEntity));

        UserDetails adminDetails =
                testDetails.loadUserByUsername(EXISTING_USERNAME);

        Assertions.assertNotNull(adminDetails);
        Assertions.assertEquals(EXISTING_USERNAME, adminDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), adminDetails.getPassword());

        Assertions.assertEquals(2,
                adminDetails.getAuthorities().size(),
                "The authorities are supposed to be just two - ADMIN/USER.");

        assertRole(adminDetails.getAuthorities(), "ROLE_ADMIN");
        assertRole(adminDetails.getAuthorities(), "ROLE_USER");

    }
    private void assertRole(Collection<? extends GrantedAuthority> authorities,
                            String role) {
        authorities.
                stream().
                filter(a -> role.equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }


    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> testDetails.loadUserByUsername(NON_EXISTING_USERNAME)
        );
    }
}
