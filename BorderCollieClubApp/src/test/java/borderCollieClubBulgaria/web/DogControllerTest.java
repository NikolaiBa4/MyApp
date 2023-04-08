package borderCollieClubBulgaria.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void getAddDog_byAnonymous_redirectsToLogin() throws Exception {

        mockMvc.perform(get("/dogs/new"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    void getAddDog_byLoggedUser_returnsProperView() throws Exception {

        mockMvc.perform(get("/dogs/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("dogs"))
                .andExpect(view().name("dogs"));
    }

    @Test
    @WithAnonymousUser
    void getAll_Annonimus_user_returnsProperView() throws Exception {

        mockMvc.perform(get("/dogs/all"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    void getAddDog_with_user_redirectsToLogin() throws Exception {

        mockMvc.perform(get("/dogs/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("dogs-add"));
    }
}
