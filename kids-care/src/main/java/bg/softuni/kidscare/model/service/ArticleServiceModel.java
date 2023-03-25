package bg.softuni.kidscare.model.service;

public class ArticleServiceModel {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;

    public ArticleServiceModel() {

    }

    public Long getId() {
        return id;
    }

    public ArticleServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleServiceModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
