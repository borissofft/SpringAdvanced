package bg.softuni.kidscare.web.rest;

import bg.softuni.kidscare.model.view.ArticleViewModel;
import bg.softuni.kidscare.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleRestController {
    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleViewModel>> getAllArticles() {

        List<ArticleViewModel> articleViewModels = this.articleService.findAllArticles();
        return ResponseEntity.ok(articleViewModels);
    }

    @GetMapping("/api/{id}/articles")
    public ResponseEntity<ArticleViewModel> getArticle(@PathVariable Long id,
                                                       @AuthenticationPrincipal UserDetails userDetails) {

        ArticleViewModel article = this.articleService.findArticleById(id);

        return ResponseEntity.ok(article);
    }
}
