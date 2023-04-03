package bg.softuni.kidscare.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistration_successful() throws Exception {
        this.mockMvc.perform(post("/users/register") // send POST request on /users/register
                        .param("username", "Pesho")
                        .param("email", "pesho@gmail.com")
                        .param("password", "12345")
                        .param("requestedType", "Педагог")
                        .with(csrf())  // the request will come with this CSRF token which comes from thymeleaf-extras-springsecurity6
                ).
                andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));

    }

    @Test
    void testRegistration_redirectRegisterNotValidEmail() throws Exception {
        this.mockMvc.perform(post("/users/register")
                        .param("username", "Pesho")
                        .param("email", "gmail.com")
                        .param("password", "12345")
                        .param("requestedType", "Педагог")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));
    }
}