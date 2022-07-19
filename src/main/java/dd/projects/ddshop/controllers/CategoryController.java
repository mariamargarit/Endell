package dd.projects.ddshop.controllers;

import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/createCategory")
    void create(Category category) {
        categoryService.createCategory(category);
    }

    @GetMapping
    @ResponseBody
    List<Category> read() {
        return categoryService.getCategories();
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody Category newCategory) {
        categoryService.updateCategory(id,newCategory);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
    }
}
