package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.mappers.CategoryMapperImpl;
import dd.projects.ddshop.mappers.SubcategoryMapper;
import dd.projects.ddshop.mappers.SubcategoryMapperImpl;
import dd.projects.ddshop.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryMapperImpl subcategoryMapper;
    private final CategoryMapperImpl categoryMapper;

    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository, SubcategoryMapperImpl subcategoryMapper, CategoryMapperImpl categoryMapper) {
        this.subcategoryRepository = subcategoryRepository;
        this.subcategoryMapper = subcategoryMapper;
        this.categoryMapper = categoryMapper;
    }

    public static Subcategory getSubcategoryFromDTO(SubcategoryDTO subcategoryDTO, Category category) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryDTO.getName());
        subcategory.setCategoryId(category);
        return subcategory;
    }

    public void createSubcategory (SubcategoryDTO subcategoryDTO) {
//        Subcategory subcategory = getSubcategoryFromDTO(subcategoryDTO, category);
//        subcategoryRepository.save(subcategory);
        subcategoryRepository.save(subcategoryMapper.toSubcategory(subcategoryDTO));
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
        subcategory.setCategoryId(categoryMapper.toCategory(newSubcategory.getCategoryId()));
        subcategoryRepository.save(subcategory);
    }
    public void deleteSubcategoryById(int id) { subcategoryRepository.deleteById(id); }
}
