package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dto.ProductDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.services.CategoryService;
import dd.projects.ddshop.services.ProductService;
import dd.projects.ddshop.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private final ProductService productService;
    private final SubcategoryService subcategoryService;

    @Autowired
    ProductController(ProductService productService, SubcategoryService subcategoryService) {
        this.productService = productService;
        this.subcategoryService = subcategoryService;
    }

    @PostMapping("/createProduct")
    ResponseEntity<Object> create(@RequestBody ProductDTO productDTO) {
        Optional<Subcategory> optionalSubcategory = subcategoryService.readSubcategory(productDTO.getSubcategoryId());
        Subcategory subcategory = optionalSubcategory.get();
        productService.createProduct(productDTO, subcategory);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    ResponseEntity<List<Product>> read() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateProduct")
    Product update(Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    void delete(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }
}
