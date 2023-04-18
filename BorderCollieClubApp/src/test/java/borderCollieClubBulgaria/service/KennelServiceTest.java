package borderCollieClubBulgaria.service;

import static org.mockito.Mockito.when;

import borderCollieClubBulgaria.domain.dto.view.KennelViewDto;
import borderCollieClubBulgaria.domain.entities.CountryEntity;
import borderCollieClubBulgaria.domain.entities.KennelEntity;
import borderCollieClubBulgaria.repository.KennelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class KennelServiceTest {


    private final Long KENNEL_ID = 1L;

    private final String COUNTRY_NAME = "Bulgaria";

    private final String COUNTRY_NOT_NAME = "Tanzania";

    private final String KENNEL_EMAIL = "sheep@mind.bg";

    private final String KENNEL_NOT_EMAIL = "dragon@mind.bg";

    private final String KENNEL_NAME = "Sheep mind";

    private final String KENNEL_NOT_NAME = "Sheep";


    private KennelViewDto kennelViewDto;

    @Mock
    private CountryService mockCountryService;

    @Mock
    private KennelRepository mockKennelRepository;


    private KennelService testKennelService;


    @BeforeEach
    void setUp() {
        testKennelService = new KennelService
                (mockKennelRepository, mockCountryService);


    }

    @Test
    void test_Kennel_Find_By_Name_Success() {


        CountryEntity country = new CountryEntity().setName(COUNTRY_NAME);

        KennelEntity kennel = new KennelEntity()
                .setName(KENNEL_NAME).setEmail(KENNEL_EMAIL).setCountry(country);

        when(mockKennelRepository.findByName(KENNEL_NAME))
                .thenReturn(Optional.of(kennel));

        KennelEntity kennelEntity = this.testKennelService
                .findKennelByName(KENNEL_NAME);

        Assertions.assertNotNull(kennelEntity);
        Assertions.assertEquals(KENNEL_NAME, kennelEntity.getName());
        Assertions.assertEquals(KENNEL_EMAIL, kennelEntity.getEmail());
        Assertions.assertEquals(COUNTRY_NAME, kennelEntity.getCountry().getName());

    }

    @Test
    void test_Kennel_Find_By_Name_Null_Invoked() {


        CountryEntity country = new CountryEntity().setName(COUNTRY_NAME);

        KennelEntity kennel = new KennelEntity().setName(KENNEL_NAME).setEmail(KENNEL_EMAIL).setCountry(country);

        when(mockKennelRepository.findByName(KENNEL_NAME)).thenReturn(Optional.of(kennel));

        KennelEntity kennelEntity = this.testKennelService.findKennelByName(KENNEL_NAME);

        Assertions.assertNull(
                testKennelService.findKennel(KENNEL_NOT_NAME, KENNEL_NOT_EMAIL, COUNTRY_NOT_NAME));
        Assertions.assertNull(testKennelService.findKennelByName(KENNEL_NOT_NAME).getEmail());
        Assertions.assertNull(testKennelService.findKennelByName(KENNEL_NOT_NAME).getName());
        Assertions.assertNull(testKennelService.findKennelByName(KENNEL_NOT_NAME).getCountry());
        Assertions.assertNotEquals(testKennelService.findKennelByName(KENNEL_NOT_NAME).getName()
                , kennelEntity.getName());
    }

    @Test
    public void find_Kennel_By_Email_Success_Invoked() {

        CountryEntity country = new CountryEntity().setName(COUNTRY_NAME);

        KennelEntity kennel = new KennelEntity().setName(KENNEL_NAME).setEmail(KENNEL_EMAIL).setCountry(country);

        when(mockKennelRepository.findByEmail(KENNEL_EMAIL)).thenReturn(Optional.of(kennel));

        KennelEntity kennelEntity = this.testKennelService.findKennelByEmail(KENNEL_EMAIL);


        Assertions.assertNotNull(kennelEntity);
        Assertions.assertEquals(KENNEL_NAME, kennelEntity.getName());
        Assertions.assertEquals(KENNEL_EMAIL, kennelEntity.getEmail());
        Assertions.assertEquals(COUNTRY_NAME, kennelEntity.getCountry().getName());


    }

    @Test
    public void map_To_View_Dto_Success_Invoked() {

        CountryEntity country = new CountryEntity(COUNTRY_NAME);

        KennelEntity kennel = new KennelEntity().
                setName(KENNEL_NAME).
                setEmail(KENNEL_EMAIL).
                setCountry(country);

        kennelViewDto = new KennelViewDto().
                setId(KENNEL_ID)
                .setName(kennel.getName()).
                setEmail(kennel.getEmail()).
                setCountryName(kennel.getCountry().getName());


        Assertions.assertNotNull(kennelViewDto);
        Assertions.assertEquals(KENNEL_NAME, kennelViewDto.getName());
        Assertions.assertEquals(KENNEL_EMAIL, kennelViewDto.getEmail());
        Assertions.assertEquals(COUNTRY_NAME, kennelViewDto.getCountryName());


    }


}
