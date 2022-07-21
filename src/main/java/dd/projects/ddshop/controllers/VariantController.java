package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.VariantDTO;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Variant;
import dd.projects.ddshop.services.ProductService;
import dd.projects.ddshop.services.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VariantController {
    private final VariantService variantService;
    private final ProductService productService;

    @Autowired
    public VariantController(VariantService variantService, ProductService productService) {
        this.variantService = variantService;
        this.productService = productService;
    }
    @PostMapping("/createVariant")
    public ResponseEntity<Object> create(@RequestBody VariantDTO variantDto) {
//        Optional<Product> optionalProduct = productService.readProduct(variantDto.getProductId());
//        Product product = optionalProduct.get();
//        variantService.createVariant(variantDto,product);
        variantService.createVariant(variantDto);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
    @GetMapping("/getVariant")
    public ResponseEntity<List<VariantDTO>> read() {
        return new ResponseEntity<>(variantService.getVariant(), HttpStatus.OK);
    }

    @PutMapping("/updateVariant/{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody VariantDTO newVariant) {
        variantService.updateVariant(id,newVariant);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @DeleteMapping("/deleteVariant/{id}")
    void delete (@PathVariable Integer id) {
        variantService.deleteVariantById(id);
    }
}
