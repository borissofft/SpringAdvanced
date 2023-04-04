package bg.softuni.kidscare.web;


import bg.softuni.kidscare.model.view.ArticleViewModel;
import bg.softuni.kidscare.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @Test
    void getAddArticleView_successful() throws Exception {
        this.mockMvc.perform(get("/articles/add")).andDo(print())
                .andExpect(view().name("article-add"));
    }

    @Test
    void getAllArticlesView_successful() throws Exception {
        this.mockMvc.perform(get("/articles/all")).andDo(print())
                .andExpect(view().name("articles"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testArticlesAddConfirm_successful() throws Exception {
        this.mockMvc.perform(post("/articles/add")
                        .param("title", "The title is right length")
                        .param("content", "Lorem ipsum dolor sit amet")
                        .param("imageUrl", "https://res.cloudinary.com")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles/all"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testArticlesAddConfirm_redirectAddNotValidTitle() throws Exception {
        this.mockMvc.perform(post("/articles/add")
                        .param("title", "The")
                        .param("content", "Lorem ipsum dolor sit amet")
                        .param("imageUrl", "https://res.cloudinary.com")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles/add"));
    }

    @Test
    public void getArticle_articleExist() throws Exception {
        when(this.articleService.findArticleById(1L)).thenReturn(createArticle("Тестовото заглавие"));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles/1"))
                .andExpect(view().name("article-details"));
    }

    @Test
    public void getArticle_articleDoesNotExist() throws Exception {
        when(this.articleService.findArticleById(1L)).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/articles/1"))
                .andExpect(status().isNotFound());

    }

    private ArticleViewModel createArticle(String title) {
        return new ArticleViewModel()
                .setTitle(title)
                .setContent("Тестово съдържание, тестово съдържание")
                .setImageUrl("https://res.cloudinary.com")
                .setId(1L);
    }

}

