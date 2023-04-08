package borderCollieClubBulgaria.service;

import borderCollieClubBulgaria.domain.entities.CountryEntity;
import borderCollieClubBulgaria.domain.entities.RoleEntity;
import borderCollieClubBulgaria.domain.entities.UserEntity;
import borderCollieClubBulgaria.domain.enums.UserRoles;
import borderCollieClubBulgaria.repository.CountryRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {


    String countryName="Bulgaria";
    @Mock
    private CountryRepository mockCountryRepository;

    private CountryService toTest;

    @BeforeEach
    void setUp() {
        toTest = new CountryService(mockCountryRepository);
    }
    @Test
   public void test_CountryRepository_SaveInvoked() {

        //LEFT for reference

        // ARRANGE
        CountryEntity country = new CountryEntity(countryName);
        when(mockCountryRepository.findByName(countryName)).thenReturn(Optional.of(country));
        //ACT
        CountryEntity countryEntity=toTest.findByName(countryName);

        //ASSERT
        Assertions.assertNotNull(countryEntity);
        Assertions.assertEquals(countryName, countryEntity.getName());
    }

    @Test
    public void test_Country_Repository_SaveInvoked() {

        //LEFT for reference

        // ARRANGE
        CountryEntity country = new CountryEntity().setName("Bulgaria");

        when(mockCountryRepository.findByName("Bulgaria"))
                .thenReturn(Optional.of(country));
        //ACT
         CountryEntity countryEntity=toTest.findByName("Bulgaria");

        //ASSERT
        Assertions.assertNotNull(countryEntity);
    }
}
