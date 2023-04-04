package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.entity.PictureEntity;
import bg.softuni.kidscare.model.entity.UserEntity;
import bg.softuni.kidscare.model.entity.UserRoleEntity;
import bg.softuni.kidscare.model.enums.UserRoleEnum;
import bg.softuni.kidscare.model.view.ProfileViewModel;
import bg.softuni.kidscare.service.ProfileService;
import bg.softuni.kidscare.util.ImageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    @MockBean
    private ImageUtil imageUtil;


    @Test
    @WithMockUser(username = "teacher", roles = "TEACHER")
    void getAddProfileView_successful() throws Exception {
        this.mockMvc.perform(get("/profiles/add")).andDo(print())
                .andExpect(view().name("profile-add"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = "{TEACHER, ADMIN}")
    void getProfileByIdView_successful() throws Exception {

        UserEntity teacher = new UserEntity();
        teacher.setUsername("teacherUser");
        teacher.setId(1L);
        UserRoleEntity role = new UserRoleEntity().setRole(UserRoleEnum.TEACHER);
        teacher.setRoles(Collections.singleton(role));

        PictureEntity picture = new PictureEntity()
                .setFileName("picture")
                .setContentType("png")
                .setContent(new byte[10]);

        when(this.profileService.findProfileById(1L)).thenReturn(createProfile("Test city 1", teacher, picture));

        this.imageUtil = new ImageUtil();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        when(this.profileService.isOwner(userDetails, 1L)).thenReturn(true);

        this.mockMvc.perform(get("/profiles/1")).andDo(print())
                .andExpect(model().size(4))
                .andExpect(model().attributeExists("profile"))
                .andExpect(model().attributeExists("imgUtil"))
                .andExpect(model().attributeExists("canDelete"))
                .andExpect(view().name("profile-details"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = "TEACHER")
    void testPartnerAddConfirm_successfully() throws Exception {
        this.mockMvc.perform(post("/profiles/add")
                        .param("city", "Test city")
                        .param("age", "18")
                        .param("phoneNumber", "888")
                        .param("description", "Test description")
                        .param("pricePerHour", "10")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("all?page=0"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = "TEACHER")
    void testPartnerAddConfirm_redirectAddNotValidDescription() throws Exception {
        this.mockMvc.perform(post("/profiles/add")
                        .param("city", "Test city")
                        .param("age", "18")
                        .param("phoneNumber", "888")
                        .param("description", "")
                        .param("pricePerHour", "10")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profiles/add"));
    }

    private ProfileViewModel createProfile(String city, UserEntity teacher, PictureEntity picture) {
        return new ProfileViewModel()
                .setCity(city)
                .setAge(20)
                .setPhoneNumber(8999)
                .setDescription("New test description")
                .setPricePerHour(BigDecimal.valueOf(20))
                .setUser(teacher)
                .setPicture(picture);
    }
}
