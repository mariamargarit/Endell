package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.ProductAttributeDTO;
import dd.projects.ddshop.entities.ProductAttribute;
import dd.projects.ddshop.services.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProductAttributeController {
    private final ProductAttributeService productAttributeService;

    @Autowired
    public ProductAttributeController(ProductAttributeService productAttributeService) {
        this.productAttributeService = productAttributeService;
    }
    @PostMapping("/createProductAttribute")
    ResponseEntity<Object> create(@RequestBody ProductAttributeDTO productAttributeDTO) {
        productAttributeService.createProductAttribute(productAttributeDTO);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllProductAttributes")
    ResponseEntity<List<ProductAttributeDTO>> read() {
        return new ResponseEntity<>(productAttributeService.getProductAttribute(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateProductAttribute/{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody ProductAttributeDTO newProductAttributeDTO) {
        productAttributeService.updateProductAttribute(id,newProductAttributeDTO);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/deleteProductAttribute/{id}")
    void delete(@PathVariable Integer id) {
        productAttributeService.deleteProductAttribute(id);
    }
}
