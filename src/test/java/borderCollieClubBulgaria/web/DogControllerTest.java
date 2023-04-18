package borderCollieClubBulgaria.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DogControllerTest {

    private final String COUNTRY_NAME = "Bulgaria";

    private final String DOG_NAME = "Kasimir";

    private final String KENNEL_EMAIL = "sheep@mind.bg";

    private final String DOG_GENDER = "Female";

    private final String KENNEL_NAME = "Sheep mind";

    private final String DOG_AGE = "100";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void get_Add_Dog_With_Anonymous_Redirects_To_Login() throws Exception {

        mockMvc.perform(get("/dogs/new"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    void get_Add_Dog_By_Logged_User_Returns_Proper_View() throws Exception {

        mockMvc.perform(get("/dogs/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("dogs"))
                .andExpect(view().name("dogs"));
    }

    @Test
    @WithAnonymousUser
    void get_All_Anonymous_User_Redirect_Login() throws Exception {

        mockMvc.perform(get("/dogs/all"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    void get_Add_Dog_With_User_Returns_Proper_View() throws Exception {

        mockMvc.perform(get("/dogs/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("dogs-add"));
    }


    @Test
    @WithAnonymousUser
    void post_Add_Dog_Without_User_Returns_Redirect_Login() throws Exception {
        mockMvc.perform(post("/dogs/new")
                        .param("name", DOG_NAME).
                        param("gender", DOG_GENDER).
                        param("kennelName", COUNTRY_NAME).
                        param("kennelCountry", COUNTRY_NAME).
                        param("kennelEmail", KENNEL_EMAIL).
                        param("age", DOG_AGE)
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrlPattern("**/login"));

    }

    @Test
    @WithAnonymousUser
    void post_Add_Dog_Without_User_With_Errors_Returns_Redirect_Login() throws Exception {
        mockMvc.perform(post("/dogs/new")
                        .param("name", "").
                        param("gender", "").
                        param("kennelName", "").
                        param("kennelCountry", "").
                        param("kennelEmail", "").
                        param("age", "")
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));

    }

    @Test
    @WithMockUser
    void test_Add_Dog_With_User_Proper_View() throws Exception {
        mockMvc.perform(post("/dogs/new")
                        .param("name", "").
                        param("gender", "").
                        param("kennelName", "").
                        param("kennelCountry", "").
                        param("kennelEmail", "").
                        param("age", "")
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/dogs/new"));

    }
}