package borderCollieClubBulgaria.service;

import borderCollieClubBulgaria.domain.dto.binding.UserRegisterFormDto;
import borderCollieClubBulgaria.domain.dto.view.KennelViewDto;
import borderCollieClubBulgaria.domain.dto.view.UserViewDto;
import borderCollieClubBulgaria.domain.entities.CountryEntity;
import borderCollieClubBulgaria.domain.entities.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private final String COUNTRY_NAME="Bulgaria";

    private final String USERNAME="GoshoMomcheLosho";

    private final String USER_EMAIL="sheep@mind.bg";

    private final String PASSWORD="Testtest";

    private final String FIRST_NAME="Gosho";

    private final String LAST_NAME="Georgiev";

    private final Integer AGE=12;



    private UserRegisterFormDto userRegisterFormDto;


    private KennelService testKennelService;




    @Test
    public void map_To_Dto_Success(){

        CountryEntity country=new CountryEntity(COUNTRY_NAME);

        UserEntity user=new UserEntity()
                .setCountryEntity(country).
                setEmail(USER_EMAIL).
                setUsername(USERNAME).
                setAge(AGE).
                setFirstName(FIRST_NAME).
                setLastName(LAST_NAME)
                .setPassword(PASSWORD);

        userRegisterFormDto=new UserRegisterFormDto()
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setAge(user.getAge())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName()).
                setPassword(user.getPassword()).
                setCountryName(user.getCountryEntity().getName());


        Assertions.assertEquals(USERNAME, userRegisterFormDto.getUsername());
        Assertions.assertEquals(USER_EMAIL, userRegisterFormDto.getEmail());
        Assertions.assertEquals(COUNTRY_NAME, userRegisterFormDto.getCountryName());
        Assertions.assertEquals(PASSWORD, userRegisterFormDto.getPassword());
        Assertions.assertEquals(AGE, userRegisterFormDto.getAge());
        Assertions.assertEquals(FIRST_NAME, userRegisterFormDto.getFirstName());
        Assertions.assertEquals(LAST_NAME, userRegisterFormDto.getLastName());
    }
}
