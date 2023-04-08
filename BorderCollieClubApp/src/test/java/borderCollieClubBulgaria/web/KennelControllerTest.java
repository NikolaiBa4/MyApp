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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class KennelControllerTest {

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

}
