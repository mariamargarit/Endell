package dd.projects.ddshop.services;

import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) { return categoryRepository.save(category); }
    public List<Category> getCategories() { return categoryRepository.findAll(); }

    public Category updateCategory(Category category) { return categoryRepository.save(category); }
    public void deleteCategoryById(int id) { categoryRepository.deleteById(id); }
}
