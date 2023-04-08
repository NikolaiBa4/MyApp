package borderCollieClubBulgaria.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("auth-register"));

    }

    @Test
    void testUserRegistrationError() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "gosho").
                        param("email", "test@ysy.bg").
                        param("firstName", "Gosho").
                        param("lastName", "Goshev").
                        param("password", "testtest").
                        param("confirmPassword", "testtesto").
                        param("countryName","Bulgaria")
                        .param("age","42")
                        .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/users/register"));

    }

    @Test
    void testUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "gosho").
                        param("email", "test@ysy.bg").
                        param("firstName", "Gosho").
                        param("lastName", "Goshev").
                        param("password", "testtest").
                        param("confirmPassword", "testtest").
                        param("countryName","Bulgaria")
                        .param("age","42")
                        .with(csrf())).
                andExpect(status().isOk()).
                andExpect(view().name("registration-success"));

    }
}
