package bg.softuni.kidscare.web.rest;

import bg.softuni.kidscare.model.view.ArticleViewModel;
import bg.softuni.kidscare.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @Test
    void getArticle_successfully() throws Exception {
        when(this.articleService.findArticleById(1L)).thenReturn(createArticle("Test content 1"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/1/articles"))
                .andExpect(status().isOk());
    }

    private ArticleViewModel createArticle(String content) {

        return new ArticleViewModel()
                .setId(1L)
                .setTitle("Test title")
                .setContent(content)
                .setImageUrl("Test imageUrl");
    }

}
