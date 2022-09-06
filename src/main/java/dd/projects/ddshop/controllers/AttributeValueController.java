package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.AttributeValueDTO;
import dd.projects.ddshop.entities.AttributeValue;
import dd.projects.ddshop.entities.ProductAttribute;
import dd.projects.ddshop.services.AttributeValueService;
import dd.projects.ddshop.services.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
public class AttributeValueController {
    private final AttributeValueService attributeValueService;

    private final ProductAttributeService productAttributeService;

    @Autowired
    public AttributeValueController (AttributeValueService attributeValueService, ProductAttributeService productAttributeService) {
        this.attributeValueService = attributeValueService;
        this.productAttributeService = productAttributeService;
    }

    @GetMapping("/getAllAttributeValues")
    public ResponseEntity<List<AttributeValueDTO>> getAttributeValue() {
        return new ResponseEntity<>(attributeValueService.getAttributeValue(), HttpStatus.OK);
    }

    @PostMapping("/createAttributeValue/{id}")
    public ResponseEntity <Object> createAttributeValue (@RequestBody AttributeValueDTO attributeValueDTO, @PathVariable Integer id){
        attributeValueService.createAttributeValue(attributeValueDTO, id);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PutMapping("/updateAttributeValue/{id}")
    public ResponseEntity<Object> updateAttributeValue (@PathVariable Integer id, @RequestBody AttributeValueDTO newAttributeValueDTO) {
        attributeValueService.updateAttributeValue(id,newAttributeValueDTO);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @DeleteMapping("/deleteAttributeValue/{id}")
    void deleteAttributeValueById (@PathVariable Integer id) {
        attributeValueService.deleteAttributeValueById(id);
    }
}
