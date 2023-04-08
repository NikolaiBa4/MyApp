package borderCollieClubBulgaria.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class KennelControllerTest {


    private final String COUNTRY_NAME="Bulgaria";

    private final String COUNTRY_NOT_NAME="Tanzania";

    private final String KENNEL_EMAIL="sheep@mind.bg";

    private final String KENNEL_NOT_EMAIL="dragon@mind.bg";

    private final String KENNEL_NAME="Sheep mind";

    private final String KENNEL_NOT_NAME="Sheep";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void getAddKennel_byAnonymous_redirectsToLogin() throws Exception {

        mockMvc.perform(get("/kennels/new"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    void getAddKennel_with_user_redirectsToLogin() throws Exception {

        mockMvc.perform(get("/kennels/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("kennels-add"));
    }

    @Test
    @WithMockUser
    void getAddKennel_byLoggedUser_returnsProperView() throws Exception {

        mockMvc.perform(get("/kennels/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("kennels"))
                .andExpect(view().name("kennels"));
    }

    @Test
    @WithMockUser
    void testKennelRegistration() throws Exception {
        mockMvc.perform(post("/kennels/new")
                        .param("name", KENNEL_NAME).
                        param("email", KENNEL_EMAIL).
                        param("countryName", COUNTRY_NAME)
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/kennels/all"));

    }

    @Test
    @WithAnonymousUser
    void testKennelRegistrationFail() throws Exception {
        mockMvc.perform(post("/kennels/new")
                        .param("name", KENNEL_NAME).
                        param("email", KENNEL_EMAIL).
                        param("countryName", COUNTRY_NAME)
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));

    }

    @Test
    @WithMockUser
    void test_Add_Dog_Without_User_Returns_Redirect() throws Exception {
        mockMvc.perform(post("/kennels/new")
                        .param("name", "").
                        param("email", "").
                        param("countryName", "")
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/kennels/new"));

    }

}
