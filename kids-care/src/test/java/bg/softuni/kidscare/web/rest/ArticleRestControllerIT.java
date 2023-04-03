package bg.softuni.kidscare.web.rest;

import bg.softuni.kidscare.model.entity.UserEntity;
import bg.softuni.kidscare.model.entity.UserRoleEntity;
import bg.softuni.kidscare.model.enums.UserRoleEnum;
import bg.softuni.kidscare.model.view.ArticleViewModel;
import bg.softuni.kidscare.service.ArticleService;
import bg.softuni.kidscare.service.UserService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleRestControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "adminUser")
    void getArticle_successfully() throws Exception {
        when(this.articleService.findArticleById(1L)).thenReturn(createArticle("Test content 1"));

        UserEntity admin = new UserEntity();
        admin.setUsername("adminUser");
        admin.setId(1L);
        UserRoleEntity role = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        admin.setRoles(Collections.singleton(role));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        when(this.articleService.isAdmin(userDetails, 1L)).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/1/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.content", is("Test content 1")))
                .andExpect(jsonPath("$.imageUrl", is("Test imageUrl")))
                .andExpect(jsonPath("$.canEdit", is(true)));
    }

    @Test
    public void getArticle_articleDoesNotExist() throws Exception {
        when(this.articleService.findArticleById(1L)).thenThrow(new ResponseStatusException(NOT_FOUND, "Unable to find resource"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/1/articles"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithAnonymousUser
    public void deleteArticle_anonymousUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/1/articles"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "adminUser")
    public void deleteArticle_withAdmin_articleIsDeletedSuccessfully() throws Exception {
        UserEntity admin = new UserEntity();
        admin.setUsername("adminUser");
        admin.setId(1L);
        UserRoleEntity role = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        admin.setRoles(Collections.singleton(role));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        when(this.articleService.isAdmin(userDetails, 1L)).thenReturn(true);

        when(this.userService.findUserByUsername("adminUser")).thenReturn(admin);
        ArticleViewModel article = createArticle("Article to be deleted!");
        when(this.articleService.findArticleById(1L)).thenReturn(article);
        when(this.articleService.deleteArticle(1L)).thenReturn(article);

        mockMvc.perform(delete("/api/1/articles")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("Article to be deleted!")));
    }

    private ArticleViewModel createArticle(String content) {

        return new ArticleViewModel()
                .setId(1L)
                .setTitle("Test title")
                .setContent(content)
                .setImageUrl("Test imageUrl");
    }

}
