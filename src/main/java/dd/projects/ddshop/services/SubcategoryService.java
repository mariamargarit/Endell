package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.mappers.CategoryMapperImpl;
import dd.projects.ddshop.mappers.SubcategoryMapperImpl;
import dd.projects.ddshop.repos.CategoryRepository;
import dd.projects.ddshop.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryMapperImpl subcategoryMapper;
    private final CategoryMapperImpl categoryMapper;
    private final CategoryRepository categoryRepository;
    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository, SubcategoryMapperImpl subcategoryMapper, CategoryMapperImpl categoryMapper, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.subcategoryMapper = subcategoryMapper;
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    public void createSubcategory (final String name, final int id) {
        final Category category = categoryRepository.getReferenceById(id);
        final Subcategory subcategory = new Subcategory(name, category);
        subcategoryRepository.save(subcategory);
    }

    public List<Subcategory> getSubcategories() { return subcategoryRepository.findAll(); }

    public List<SubcategoryDTO> getSubcategory() {
        return subcategoryRepository.findAll()
                .stream()
                .map(subcategoryMapper::toSubcategoryDTO)
                .collect(toList());
    }

    public Subcategory readSubcategory(Integer id) {
        return subcategoryRepository.getReferenceById(id);
    }
    public void updateSubcategory(int subcategoryId, SubcategoryDTO newSubcategory) {
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).get();
        subcategory.setName(newSubcategory.getName());
        subcategoryRepository.save(subcategory);
    }
    public void deleteSubcategoryById(int id) { subcategoryRepository.deleteById(id); }
}
