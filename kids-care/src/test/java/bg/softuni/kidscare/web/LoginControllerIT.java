package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.entity.UserEntity;
import bg.softuni.kidscare.model.entity.UserRoleEntity;
import bg.softuni.kidscare.model.enums.UserRoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getLoginView_successful() throws Exception {
        this.mockMvc.perform(get("/users/login")).andDo(print())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username = "adminUser")
    void testLogin_successful() throws Exception {

        UserEntity admin = new UserEntity();
        admin.setUsername("adminUser");
        admin.setId(1L);
        UserRoleEntity role = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        admin.setRoles(Collections.singleton(role));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        String password = userDetails.getPassword();

        this.mockMvc.perform(post("/users/login")
                        .param("username", username)
                        .param("password", password)
                        .with(csrf())
                ).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithAnonymousUser
    void testLogin_loginError() throws Exception {

        this.mockMvc.perform(post("/users/login-error")
                        .param("username", "111")
                        .param("password", "111")
                        .with(csrf())
                ).
                andExpect(status().isNotFound());
    }

}
