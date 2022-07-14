package dd.projects.ddshop.controllers;

import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
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
    Category update(Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
    }
}
