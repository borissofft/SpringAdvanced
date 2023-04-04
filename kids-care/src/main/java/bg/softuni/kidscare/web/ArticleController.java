package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.binding.ArticleAddBindingModel;
import bg.softuni.kidscare.model.service.ArticleServiceModel;
import bg.softuni.kidscare.model.view.ArticleViewModel;
import bg.softuni.kidscare.service.ArticleService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @Autowired
    public ArticleController(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        return "article-add";
    }

    @GetMapping("/{id}")
    public String getProfileById(@PathVariable Long id,
                                 @AuthenticationPrincipal UserDetails userDetails,
                                 Model model) {
        try {
            ArticleViewModel article = this.articleService.findArticleById(id);
            article.setCanEdit(this.articleService.isAdmin(userDetails, id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
//        model.addAttribute("article", this.articleService.findArticleById(id));
//        model.addAttribute("canDelete", this.articleService.isAdmin(userDetails, id));

        return "article-details";
    }

    @GetMapping("all")
    public String allArticles(Model model) {
        List<ArticleViewModel> articleViewModels = this.articleService.findAllArticles();
        model.addAttribute("articles", articleViewModels);
        return "articles";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ArticleAddBindingModel articleAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("articleAddBindingModel", articleAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.articleAddBindingModel",
                            bindingResult);

            return "redirect:/articles/add";
        }

        this.articleService
                .addArticle(this.modelMapper.map(articleAddBindingModel, ArticleServiceModel.class));

        return "redirect:/articles/all";
    }

    @ModelAttribute
    ArticleAddBindingModel articleAddBindingModel() {
        return new ArticleAddBindingModel();
    }
}
