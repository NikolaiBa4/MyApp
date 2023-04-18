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


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void get_Edit_User_With_Anonymous_Returns_Login() throws Exception {

        mockMvc.perform(get("/my-profile/edit"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    void test_Edit_User_With_User_Proper_Redirection() throws Exception {
        mockMvc.perform(post("/my-profile/edit")
                        .param("username", "").
                        param("firstName", "").
                        param("lastName", "").
                        param("email", "").
                        param("password", "").
                        param("age", "")
                        .param("confirmPassword", "")
                        .param("countryName", "")
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/my-profile/edit"));

    }

    @Test
    @WithAnonymousUser
    void test_Edit_User_With_Anonymous_User_Returns_Login() throws Exception {
        mockMvc.perform(post("/my-profile/edit")
                        .param("username", "").
                        param("firstName", "").
                        param("lastName", "").
                        param("email", "").
                        param("password", "").
                        param("age", "")
                        .param("confirmPassword", "")
                        .param("countryName", "")
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));

    }

}
