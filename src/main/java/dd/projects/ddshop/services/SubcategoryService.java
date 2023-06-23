package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.mappers.CategoryMapper;
import dd.projects.ddshop.mappers.SubcategoryMapper;
import dd.projects.ddshop.repos.CategoryRepository;
import dd.projects.ddshop.repos.SubcategoryRepository;
import dd.projects.ddshop.validators.SubcategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryMapper subcategoryMapper;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final SubcategoryValidator subcategoryValidator;
    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository, SubcategoryMapper subcategoryMapper, CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.subcategoryMapper = subcategoryMapper;
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
        this.subcategoryValidator = new SubcategoryValidator(subcategoryRepository);
    }

    public void createSubcategory (final String name, final Integer id) {
        subcategoryValidator.validateSubcategory(name, id);
        final Category category = categoryRepository.getReferenceById(id);
        final Subcategory subcategory = new Subcategory(name, category);
        subcategoryRepository.save(subcategory);
    }

    public SubcategoryDTO getSubcategory(Integer id) { return subcategoryMapper.toSubcategoryDTO(subcategoryRepository.getReferenceById(id)); }

    public List<SubcategoryDTO> getSubcategories() {
        return subcategoryRepository.findAll()
                .stream()
                .map(subcategoryMapper::toSubcategoryDTO)
                .collect(toList());
    }

    public Subcategory readSubcategory(Integer id) {
        return subcategoryRepository.getReferenceById(id);
    }
    public void updateSubcategory(Integer subcategoryId, SubcategoryDTO newSubcategory) {
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).get();
        subcategory.setName(newSubcategory.getName());
        subcategoryRepository.save(subcategory);
    }
    public void deleteSubcategoryById(Integer id) { subcategoryRepository.deleteById(id); }
}
