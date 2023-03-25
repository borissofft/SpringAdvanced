package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.binding.ArticleAddBindingModel;
import bg.softuni.kidscare.model.service.ArticleServiceModel;
import bg.softuni.kidscare.model.view.ArticleViewModel;
import bg.softuni.kidscare.service.ArticleService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

            return "redirect:add";
        }

        this.articleService
                .addArticle(this.modelMapper.map(articleAddBindingModel, ArticleServiceModel.class));

        return "redirect:all";
    }

    @ModelAttribute
    ArticleAddBindingModel articleAddBindingModel() {
        return new ArticleAddBindingModel();
    }
}
