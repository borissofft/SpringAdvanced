package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.entity.ArticleEntity;
import bg.softuni.kidscare.model.service.ArticleServiceModel;
import bg.softuni.kidscare.model.view.ArticleViewModel;
import bg.softuni.kidscare.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
