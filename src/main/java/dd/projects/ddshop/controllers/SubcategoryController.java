package dd.projects.ddshop.controllers;

import dd.projects.ddshop.entities.Address;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @PostMapping("/createSubcategory")
    ResponseEntity<Object> create(@RequestBody Subcategory subcategory) {
        subcategoryService.createSubcategory(subcategory);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllSubcategories")
    ResponseEntity<List<Subcategory>> read() {
        return new ResponseEntity<>(subcategoryService.getSubcategories(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateSubcategory/{id}")
    ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Subcategory subcategory) {
        subcategoryService.updateSubcategory(id, subcategory);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteSubcategory/{id}")
    void delete(@PathVariable Integer id) {
        subcategoryService.deleteSubcategoryById(id);
    }
}
