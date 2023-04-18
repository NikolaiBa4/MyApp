package borderCollieClubBulgaria.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void get_All_Users_by_Anonymous_redirectsToLogin() throws Exception {

        mockMvc.perform(get("/pages/admins/allUsers"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }


    @Test
    @WithMockUser(roles = {"ADMIN", "USER"})
    void get_User_By_Id_Users_Success() throws Exception {

        mockMvc.perform(get("/pages/admins/1")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("canDelete"))
                .andExpect(view().name("user-details"));

    }

    @Test
    @WithAnonymousUser
    void get_User_Returns_login() throws Exception {

        mockMvc.perform(get("/pages/admins/1")).
                andDo(print()).
                andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));

    }
}
