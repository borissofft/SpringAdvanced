package bg.softuni.errors.web;

import bg.softuni.errors.model.ProductNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsController {

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable("id") Long id) {
        throw new ProductNotFoundException(id);
    }

    @GetMapping("/products")
    public String getProducts() {
        throw new NullPointerException("OOOPS bug"); // Here ExceptionHandler don't catch NullPointerException
    }

    @ExceptionHandler(ProductNotFoundException.class) // This ExceptionHandler works only in the current Controller with exception classes which are defined in the brackets
    public ModelAndView onProductNotFound(ProductNotFoundException pnfe) {
        ModelAndView modelAndView = new ModelAndView("product-not-found");

        modelAndView.addObject("productId", pnfe.getProductId());

        return modelAndView;
    }

}
