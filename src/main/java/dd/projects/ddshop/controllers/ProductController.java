package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.BrandDTO;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.services.ProductService;
import dd.projects.ddshop.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
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
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/getProductsBySubcategory/{id}")
    public ResponseEntity<List<ProductDTO>> getProductBySubcategory(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.getProductsBySubcategory(id), HttpStatus.OK);
    }

    @GetMapping("/getProductsByBrand/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsByBrand(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.getProductsByBrand(id), HttpStatus.OK);
    }

    @GetMapping("/getProductsByBrandName/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsByBrandName(@PathVariable String id) {
        return new ResponseEntity<>(productService.getProductsByBrandName(id), HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody ProductDTO newProductDTO) {
        productService.updateProduct(id,newProductDTO);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void delete (@PathVariable Integer id) {
        productService.deleteProductById(id);
    }


    @PostMapping("/createBrand")
    public ResponseEntity<Object> createBrand(@RequestBody BrandDTO brandDto) {
        productService.createBrand(brandDto);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllBrands")
    public ResponseEntity<List<BrandDTO>> readBrand() {
        return new ResponseEntity<>(productService.getBrands(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBrand/{id}")
    public void deleteBrand (@PathVariable Integer id) {
        productService.deleteBrandById(id);
    }

    @PutMapping("/addBrandToProduct/{id}")
    public ResponseEntity<Object> addBrandToProduct (@RequestBody Integer brandId, @PathVariable Integer id) {
        productService.addBrandToProduct(brandId,id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}