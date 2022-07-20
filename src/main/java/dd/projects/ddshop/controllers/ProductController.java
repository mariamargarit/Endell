package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.mappers.SubcategoryDTOMapper;
import dd.projects.ddshop.mappers.SubcategoryMapper;
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

    @Autowired
    SubcategoryService subcategoryService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Object> create(@RequestBody ProductDTO productDto) {
        Subcategory subcategory = subcategoryService.readSubcategory(productDto.getSubcategoryId().getCategoryId());
        productService.createProduct(productDto, subcategory);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDTO>> read() {
        return new ResponseEntity<>(productService.getProduct(), HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody Product newProduct) {
        productService.updateProduct(id,newProduct);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    void delete (@PathVariable Integer id) {
        productService.deleteProductById(id);
    }
}