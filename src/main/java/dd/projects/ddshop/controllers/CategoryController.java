package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/createCategory")
    ResponseEntity<Object> create(@RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllCategories")
    ResponseEntity<List<CategoryDTO>> read() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getCategory/{name}")
    ResponseEntity<CategoryDTO> getCategory(@PathVariable String name) {
        return new ResponseEntity<>(categoryService.getCategory(name), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody CategoryDTO newCategoryDTO) {
        categoryService.updateCategory(id,newCategoryDTO);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    void delete(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
    }
}
