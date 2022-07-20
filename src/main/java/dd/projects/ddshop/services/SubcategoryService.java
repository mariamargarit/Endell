package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.mappers.SubcategoryMapper;
import dd.projects.ddshop.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    SubcategoryMapper subcategoryMapper = new SubcategoryMapper();

    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    public static Subcategory getSubcategoryFromDTO(SubcategoryDTO subcategoryDTO, Category category) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryDTO.getName());
        subcategory.setCategoryId(category);
        return subcategory;
    }

    public void createSubcategory (SubcategoryDTO subcategoryDTO, Category category) {
        Subcategory subcategory = getSubcategoryFromDTO(subcategoryDTO, category);
        subcategoryRepository.save(subcategory);
    }

    public List<Subcategory> getSubcategories() { return subcategoryRepository.findAll(); }
    public List<SubcategoryDTO> getSubcategory() {
        return subcategoryRepository.findAll()
                .stream()
                .map(subcategoryMapper::trans)
                .collect(toList());
    }

    public Subcategory readSubcategory(Integer id) {
        return subcategoryRepository.getReferenceById(id);
    }
    public void updateSubcategory(int subcategoryId, Subcategory newSubcategory) {
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).get();
        subcategory.setName(newSubcategory.getName());
        subcategory.setCategoryId(newSubcategory.getCategoryId());
        subcategoryRepository.save(subcategory);
    }
    public void deleteSubcategoryById(int id) { subcategoryRepository.deleteById(id); }
}
