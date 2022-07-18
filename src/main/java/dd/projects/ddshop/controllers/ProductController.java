package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dto.ProductDTO;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Subcategory;
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
        Optional<Subcategory> optionalSubcategory = subcategoryService.readSubcategory(productDTO.getSubcategoryId().getId());
        Subcategory subcategory = optionalSubcategory.get();
        productService.createProduct(productDTO, subcategory);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    ResponseEntity<List<ProductDTO>> read() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Object> updateProduct (@PathVariable Integer id, @RequestBody Product newProduct) {
        productService.updateProduct(id,newProduct);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    void delete(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }
}
