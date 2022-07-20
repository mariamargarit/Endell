package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.mappers.CategoryMapper;
import dd.projects.ddshop.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    CategoryMapper categoryMapper = new CategoryMapper();

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) { return categoryRepository.save(category); }
    public Category readCategory(Integer id) { return categoryRepository.getReferenceById(id); }
    public List<CategoryDTO> getCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::trans)
                .collect(toList());
    }

    public void updateCategory(int categoryId, Category newCategory) {
        Category category = categoryRepository.findById(categoryId).get();
        category.setName(newCategory.getName());
        categoryRepository.save(category);
    }
    public void deleteCategoryById(int id) { categoryRepository.deleteById(id); }
}
