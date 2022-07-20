package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.services.CategoryService;
import dd.projects.ddshop.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubcategoryController {

    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService, CategoryService categoryService) {
        this.subcategoryService = subcategoryService;
        this.categoryService = categoryService;
    }

    @PostMapping("/createSubcategory")
    ResponseEntity<Object> create(@RequestBody SubcategoryDTO subcategoryDTO) {
//        Category category = categoryService.readCategory(subcategoryDTO.getCategoryId());
//        subcategoryService.createSubcategory(subcategoryDTO, category);
        subcategoryService.createSubcategory(subcategoryDTO);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllSubcategories")
    ResponseEntity<List<SubcategoryDTO>> read() {
        return new ResponseEntity<>(subcategoryService.getSubcategory(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateSubcategory/{id}")
    ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody SubcategoryDTO subcategoryDTO) {
        subcategoryService.updateSubcategory(id, subcategoryDTO);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteSubcategory/{id}")
    void delete(@PathVariable Integer id) {
        subcategoryService.deleteSubcategoryById(id);
    }
}
