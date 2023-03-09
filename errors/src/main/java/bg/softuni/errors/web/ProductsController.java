package bg.softuni.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {

    @GetMapping("/products/{id}")
    public String getProductById(Long productId) {
        throw new NullPointerException("OOOPS bug");
    }

}
