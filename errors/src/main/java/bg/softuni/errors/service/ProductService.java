package bg.softuni.errors.service;

import bg.softuni.errors.model.ProductDto;
import bg.softuni.errors.model.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductDto getProductById(Long productId) {
        throw new ProductNotFoundException(productId);
    }

}
