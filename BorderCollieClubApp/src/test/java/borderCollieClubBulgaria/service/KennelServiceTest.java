package borderCollieClubBulgaria.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import borderCollieClubBulgaria.domain.dto.binding.KennelAddDto;
import borderCollieClubBulgaria.domain.entities.KennelEntity;
import borderCollieClubBulgaria.repository.CountryRepository;
import borderCollieClubBulgaria.repository.KennelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class KennelServiceTest {

    @Mock
    private CountryService mockCountryService;

    @Mock
    private KennelRepository mockKennelRepository;

    @Mock
    CountryRepository mockCountryRepository;

    @Captor
    private ArgumentCaptor<KennelEntity> kennelArgumentCaptor;

    private KennelService testKennelService;



    @BeforeEach
    void setUp() {
        testKennelService = new KennelService
                (mockKennelRepository,mockCountryService);

        mockCountryService=new CountryService(mockCountryRepository);

    }
    @Test
    void testUserRegistration_SaveInvoked() {


        KennelAddDto testRegistrationDTO = new KennelAddDto().
                setName("Sheep mind").
                setCountryName("Bulgaria").
                setEmail("sheep@mind.bg");

        this.testKennelService.addKennel(testRegistrationDTO);



        verify(mockKennelRepository).save(any());
    }




}
