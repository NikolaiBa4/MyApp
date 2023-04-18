package borderCollieClubBulgaria.service;

import borderCollieClubBulgaria.domain.entities.CountryEntity;
import borderCollieClubBulgaria.repository.CountryRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {


    private final String COUNTRY = "Bulgaria";
    @Mock
    private CountryRepository mockCountryRepository;

    private CountryService toTest;

    @BeforeEach
    void setUp() {
        toTest = new CountryService(mockCountryRepository);
    }

    @Test
    public void test_CountryRepository_Save_Invoked() {

        CountryEntity country = new CountryEntity(COUNTRY);
        when(mockCountryRepository.findByName(COUNTRY)).thenReturn(Optional.of(country));

        CountryEntity countryEntity = toTest.findByName(COUNTRY);

        Assertions.assertNotNull(countryEntity);
        Assertions.assertEquals(COUNTRY, countryEntity.getName());
    }

    @Test
    public void test_Country_Repository_Save_Invoked() {

        CountryEntity country = new CountryEntity().setName(COUNTRY);

        when(mockCountryRepository.findByName(COUNTRY))
                .thenReturn(Optional.of(country));

        CountryEntity countryEntity = toTest.findByName(COUNTRY);

        Assertions.assertNotNull(countryEntity);
    }
}
