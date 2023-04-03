package bg.softuni.kidscare.web;

import bg.softuni.kidscare.service.PsychologistService;
import bg.softuni.kidscare.util.ImageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PsychologistControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PsychologistService psychologistService;
    @MockBean
    private ImageUtil imageUtil;


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void getAddPsychologistView_successful() throws Exception {
        this.mockMvc.perform(get("/psychologists/add")).andDo(print())
                .andExpect(view().name("psychologist-add"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPsychologistAddConfirm_successful() throws Exception {
        this.mockMvc.perform(post("/psychologists/add")
                                .param("firstName", "Ivan")
                                .param("lastName", "Ivanov")
                                .param("email", "ivan@abv.bg")
                                .param("description", "asdasdasdasd")
//                        .param("image")
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("psychologists/all"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPsychologistAddConfirm_redirectAddNotValidEmail() throws Exception {
        this.mockMvc.perform(post("/psychologists/add")
                                .param("firstName", "Ivan")
                                .param("lastName", "Ivanov")
                                .param("email", "bg")
                                .param("description", "asdasdasdasd")
//                        .param("image")
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/psychologists/add"));
    }

}