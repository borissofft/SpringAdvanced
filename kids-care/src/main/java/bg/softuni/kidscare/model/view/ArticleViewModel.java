package bg.softuni.kidscare.model.view;

public class ArticleViewModel {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;

    public ArticleViewModel() {

    }

    public Long getId() {
        return id;
    }

    public ArticleViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
