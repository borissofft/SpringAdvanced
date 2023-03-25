package bg.softuni.kidscare.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity {
    private String title;
    private String content;
    private String imageUrl;

    public ArticleEntity() {

    }

    @Column(name = "title", length = 30, nullable = false)
    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(name = "content", columnDefinition = "LONGTEXT", nullable = false)
    public String getContent() {
        return content;
    }

    public ArticleEntity setContent(String content) {
        this.content = content;
        return this;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
