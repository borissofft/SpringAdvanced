package bg.softuni.kidscare.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ArticleAddBindingModel {

    private String title;
    private String content;
    private String imageUrl;

    public ArticleAddBindingModel() {

    }

    @NotBlank
    @Size(min = 10, max = 30)
    public String getTitle() {
        return title;
    }

    public ArticleAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @NotBlank
    @Size(min = 20)
    public String getContent() {
        return content;
    }

    public ArticleAddBindingModel setContent(String content) {
        this.content = content;
        return this;
    }

    @NotBlank
    @Size(min = 10, max = 255)
    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
