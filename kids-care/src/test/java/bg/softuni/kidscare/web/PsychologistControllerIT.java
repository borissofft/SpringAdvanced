package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.entity.PictureEntity;
import bg.softuni.kidscare.model.view.PsychologistViewModel;
import bg.softuni.kidscare.service.PsychologistService;
import bg.softuni.kidscare.util.ImageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
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
    void getAllPsychologistsView_successful() throws Exception {

        PictureEntity picture1 = new PictureEntity()
                .setFileName("picture1")
                .setContentType("png")
                .setContent(new byte[10]);

        PictureEntity picture2 = new PictureEntity()
                .setFileName("picture2")
                .setContentType("jpeg")
                .setContent(new byte[5]);

        when(this.psychologistService.findAllPsychologists())
                .thenReturn(new ArrayList<>(List.of(
                        createPsychologist("Test psychologist 1", picture1),
                        createPsychologist("Test psychologist 2", picture2)
                )));

        this.imageUtil = new ImageUtil();

        this.mockMvc.perform(get("/psychologists/all")).andDo(print())
                .andExpect(view().name("psychologists"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPsychologistAddConfirm_successful() throws Exception {
        this.mockMvc.perform(post("/psychologists/add")
                        .param("firstName", "Ivan")
                        .param("lastName", "Ivanov")
                        .param("email", "ivan@abv.bg")
                        .param("description", "asdasdasdasd")
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
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/psychologists/add"));
    }

    private PsychologistViewModel createPsychologist(String firstName, PictureEntity picture) {
        return new PsychologistViewModel()
                .setFirstName(firstName)
                .setLastName("Last name")
                .setEmail("test@abv.bg")
                .setDescription("Test description")
                .setId(1L)
                .setPicture(picture);
    }


}