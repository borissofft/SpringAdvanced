package bg.softuni.errors.web;

import bg.softuni.errors.model.ProductDto;
import bg.softuni.errors.model.ProductErrorDto;
import bg.softuni.errors.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        throw new ProductNotFoundException(id);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductErrorDto> onProductNotFound(ProductNotFoundException pnfe) {

        ProductErrorDto productErrorDto = new ProductErrorDto(pnfe.getProductId(), "Product not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productErrorDto);

    }



}
