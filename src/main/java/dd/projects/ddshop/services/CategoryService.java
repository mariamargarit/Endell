package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.mappers.CategoryMapperImpl;
import dd.projects.ddshop.repos.CategoryRepository;
import dd.projects.ddshop.validators.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapperImpl categoryMapper;
    private final CategoryValidator categoryValidator;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapperImpl categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.categoryValidator = new CategoryValidator(categoryRepository);
    }

    public Category createCategory(CategoryDTO categoryDTO) {
        categoryValidator.validateCategory(categoryDTO);
        return categoryRepository.save(categoryMapper.toCategory(categoryDTO));
    }
    public Category readCategory(Integer id) { return categoryRepository.getReferenceById(id); }
    public List<CategoryDTO> getCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryDTO)
                .collect(toList());
    }

    public void updateCategory(int categoryId, CategoryDTO newCategoryDTO) {
        Category category = categoryRepository.findById(categoryId).get();
        category.setName(newCategoryDTO.getName());
        categoryRepository.save(category);
    }
    public void deleteCategoryById(int id) { categoryRepository.deleteById(id); }
}
