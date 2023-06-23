package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.VariantCreationDTO;
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
@CrossOrigin("http://localhost:4200")
public class VariantController {
    private final VariantService variantService;
    private final ProductService productService;

    @Autowired
    public VariantController(VariantService variantService, ProductService productService) {
        this.variantService = variantService;
        this.productService = productService;
    }
    @PostMapping("/createVariant/{id}")
    public ResponseEntity<Object> create(@RequestBody VariantCreationDTO variantDto, @PathVariable Integer id) {
        variantService.createVariant(variantDto, id);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllVariants")
    public ResponseEntity<List<VariantDTO>> read() {
        return new ResponseEntity<>(variantService.getVariants(), HttpStatus.OK);
    }

    @GetMapping("/getVariant/{id}")
    public ResponseEntity<VariantDTO> getVariant(@PathVariable Integer id) {
        return new ResponseEntity<>(variantService.getVariant(id), HttpStatus.OK);
    }

    @GetMapping("/getVariantsBySubcategory/{id}")
    public ResponseEntity<List<VariantDTO>> getVariantsBySubcategory(@PathVariable Integer id) {
        return new ResponseEntity<>(variantService.getVariantsBySubcategory(id), HttpStatus.OK);
    }

    @GetMapping("/getVariantsByBrand/{id}")
    public ResponseEntity<List<VariantDTO>> getVariantsByBrand(@PathVariable Integer id) {
        return new ResponseEntity<>(variantService.getVariantsByBrand(id), HttpStatus.OK);
    }

    @GetMapping("/getVariantsByBrandName/{id}")
    public ResponseEntity<List<VariantDTO>> getVariantsByBrandName(@PathVariable String id) {
        return new ResponseEntity<>(variantService.getVariantsByBrandName(id), HttpStatus.OK);
    }

    @PutMapping("/updateVariant/{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody VariantDTO newVariant) {
        variantService.updateVariant(id,newVariant);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @PutMapping("/addAssignedValuesToVariant/{id}")
    public ResponseEntity<Object> addAssignedValuesToVariant (@RequestBody Integer assignedValueId, @PathVariable Integer id) {
        variantService.addAssignedValuesToVariant(assignedValueId,id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/deleteVariant/{id}")
    void delete (@PathVariable Integer id) {
        variantService.deleteVariantById(id);
    }
}
