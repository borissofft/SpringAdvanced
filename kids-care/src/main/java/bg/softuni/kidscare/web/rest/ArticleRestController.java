package bg.softuni.kidscare.web.rest;

import bg.softuni.kidscare.model.view.ArticleViewModel;
import bg.softuni.kidscare.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleRestController {
    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/api/{id}/articles")
    public ResponseEntity<ArticleViewModel> getArticle(@PathVariable("id") Long id,
                                                       @AuthenticationPrincipal UserDetails userDetails) {

        ArticleViewModel article = this.articleService.findArticleById(id);
        article.setCanEdit(this.articleService.isAdmin(userDetails, id));

        return ResponseEntity.ok(article);
    }

    @PreAuthorize("@articleService.isAdmin(#userDetails, #id)")
    @DeleteMapping("/api/{id}/articles")
    public ResponseEntity<ArticleViewModel> deleteArticle(@PathVariable("id") Long id,
                                                          @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(this.articleService.deleteArticle(id));
    }

}
