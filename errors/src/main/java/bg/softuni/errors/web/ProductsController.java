package bg.softuni.errors.web;

import bg.softuni.errors.model.ProductDto;
import bg.softuni.errors.model.ProductNotFoundException;
import bg.softuni.errors.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsController {

    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model) {

        ProductDto productDto = this.productService.getProductById(id);
        model.addAttribute("productDto", productDto);

        return "product-detail";
    }

    @GetMapping("/products")
    public String getProducts() {
        throw new NullPointerException("OOOPS bug"); // Here ExceptionHandler don't catch NullPointerException
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND) // Don't forget to return right Status code for your custom exception
    @ExceptionHandler(ProductNotFoundException.class) // This ExceptionHandler works only in the current Controller with exception classes which are defined in the brackets
    public ModelAndView onProductNotFound(ProductNotFoundException pnfe) {   // Here we can add: HttpServletRequest, HttpServletResponse, HttpSession, Principal

        ModelAndView modelAndView = new ModelAndView("product-not-found"); // ModelAndView have to be created in the method body, can't be passed as argument!

        modelAndView.addObject("productId", pnfe.getProductId());

        return modelAndView;
    }

}
