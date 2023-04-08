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


    private final String COUNTRY_NAME = "Bulgaria";

    private final String KENNEL_EMAIL = "sheep@mind.bg";

    private final String KENNEL_NAME = "Sheep mind";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void get_Add_Kennel_By_Anonymous_Redirect_Login() throws Exception {

        mockMvc.perform(get("/kennels/new"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    void get_Add_Kennel_With_User_Success() throws Exception {

        mockMvc.perform(get("/kennels/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("kennels-add"));
    }

    @Test
    @WithMockUser
    void get_Add_Kennel_With_User_Returns_Proper_View() throws Exception {

        mockMvc.perform(get("/kennels/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("kennels"))
                .andExpect(view().name("kennels"));
    }

    @Test
    @WithMockUser
    void post_Add_Kennel_With_User_Returns_Success() throws Exception {
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
    void post_Add_Kennel_Without_User_Redirect_Login() throws Exception {
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
    void test_Add_Dog_Without_User_Success() throws Exception {
        mockMvc.perform(post("/kennels/new")
                        .param("name", "").
                        param("email", "").
                        param("countryName", "")
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/kennels/new"));

    }

}
