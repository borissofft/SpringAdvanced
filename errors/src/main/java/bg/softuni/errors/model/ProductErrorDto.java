package bg.softuni.errors.model;

public class ProductErrorDto {
    private final Long productId;
    private final String description;

    public ProductErrorDto(Long productId, String description) {
        this.productId = productId;
        this.description = description;
    }


    public Long getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }
}

