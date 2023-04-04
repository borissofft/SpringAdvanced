package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.view.PartnerViewModel;
import bg.softuni.kidscare.service.PartnerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
public class PartnerControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartnerService partnerService;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void getAddPartnerView_successful() throws Exception {
        this.mockMvc.perform(get("/partners/add")).andDo(print())
                .andExpect(view().name("partner-add"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void getAllPartnersView_successful() throws Exception {

        when(this.partnerService.findAllPartners())
                .thenReturn(new ArrayList<>(List.of(
                        createPartner("Test partner 1"),
                        createPartner("Test partner 2")
                )));

        this.mockMvc.perform(get("/partners/all")).andDo(print())
                .andExpect(view().name("partners"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPartnerAddConfirm_successful() throws Exception {
        this.mockMvc.perform(post("/partners/add")
                        .param("name", "Test name")
                        .param("description", "Test description")
                        .param("partnerUrl", "www.sofia.bg")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/partners/all"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPartnerAddConfirm_redirectAddNotValidDescription() throws Exception {
        this.mockMvc.perform(post("/partners/add")
                        .param("name", "Test name")
                        .param("description", "")
                        .param("partnerUrl", "www.sofia.bg")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/partners/add"));
    }

    private PartnerViewModel createPartner(String name) {
        return new PartnerViewModel()
                .setName(name)
                .setDescription("Test partner description")
                .setPartnerUrl("test_partner.com");
    }

}
