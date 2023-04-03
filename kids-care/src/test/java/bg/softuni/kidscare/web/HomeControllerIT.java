package bg.softuni.kidscare.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getIndexView_successful() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print())
                .andExpect(view().name("index"));
    }

    @Test
    void getAboutView_successful() throws Exception {
        this.mockMvc.perform(get("/about")).andDo(print())
                .andExpect(view().name("about"));
    }

    @Test
    void getContactsView_successful() throws Exception {
        this.mockMvc.perform(get("/contacts")).andDo(print())
                .andExpect(view().name("contacts"));
    }

    @Test
    void getInProgressView_successful() throws Exception {
        this.mockMvc.perform(get("/in-progress")).andDo(print())
                .andExpect(view().name("in-progress"));
    }
}
