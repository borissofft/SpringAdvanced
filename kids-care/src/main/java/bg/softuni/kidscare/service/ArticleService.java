package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.entity.ArticleEntity;
import bg.softuni.kidscare.model.enums.UserRoleEnum;
import bg.softuni.kidscare.model.service.ArticleServiceModel;
import bg.softuni.kidscare.model.view.ArticleViewModel;
import bg.softuni.kidscare.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }


    public void addArticle(ArticleServiceModel articleServiceModel) {
        this.articleRepository
                .save(this.modelMapper.map(articleServiceModel, ArticleEntity.class));
    }

    public List<ArticleViewModel> findAllArticles() {
        return this.articleRepository
                .findAll()
                .stream()
                .map(articleEntity -> this.modelMapper.map(articleEntity, ArticleViewModel.class))
                .toList();
    }

    public ArticleViewModel findArticleById(Long id) {
        return this.articleRepository
                .findById(id)
                .map(articleEntity -> this.modelMapper.map(articleEntity, ArticleViewModel.class))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find resource"));
    }

    public boolean isAdmin(UserDetails userDetails, Long id) {

        if (id == null || userDetails == null) {
            return false;
        }

        ArticleEntity article = this.articleRepository.
                findById(id).
                orElse(null);

        if (article == null) {
            return false;
        }

        return userDetails.getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                anyMatch(a -> a.equals("ROLE_" + UserRoleEnum.ADMIN.name()));
    }

    public ArticleViewModel deleteArticle(Long id) {
        ArticleViewModel article = this.findArticleById(id);
        this.articleRepository.deleteById(id);
        return article;
    }
}
