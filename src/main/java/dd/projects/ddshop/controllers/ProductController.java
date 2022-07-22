package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.services.ProductService;
import dd.projects.ddshop.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    private final SubcategoryService subcategoryService;

    @Autowired
    public ProductController(ProductService productService, SubcategoryService subcategoryService) {
        this.productService = productService;
        this.subcategoryService = subcategoryService;
    }

    @PostMapping("/createProduct/{id}")
    public ResponseEntity<Object> create(@RequestBody ProductDTO productDto, @PathVariable Integer id) {
        productService.createProduct(productDto, id);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDTO>> read() {
        return new ResponseEntity<>(productService.getProduct(), HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody ProductDTO newProductDTO) {
        productService.updateProduct(id,newProductDTO);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    void delete (@PathVariable Integer id) {
        productService.deleteProductById(id);
    }
}