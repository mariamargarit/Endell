package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.mappers.CategoryMapper;
import dd.projects.ddshop.repos.CategoryRepository;
import dd.projects.ddshop.validators.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryValidator categoryValidator;
    private final SubcategoryService subcategoryService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, SubcategoryService subcategoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.categoryValidator = new CategoryValidator(categoryRepository);
        this.subcategoryService = subcategoryService;
    }

    public Category createCategory(CategoryDTO categoryDTO) {
        categoryValidator.validateCategory(categoryDTO);
        return categoryRepository.save(categoryMapper.toCategory(categoryDTO));
    }

    public CategoryDTO getCategory(String name) {
        return categoryMapper.toCategoryDTO(categoryRepository.findCategoryByName(name));
    }

    public List<CategoryDTO> getCategories() {
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
    public void deleteCategoryById(int id) {
        Category category = categoryRepository.findById(id).get();
        for(int i = 0; i < category.getSubcategories().size(); i++){
            subcategoryService.deleteSubcategoryById(category.getSubcategories().get(i).getId());
        }
        categoryRepository.deleteById(id);
    }
}
