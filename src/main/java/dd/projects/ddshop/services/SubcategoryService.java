package dd.projects.ddshop.services;

import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    public Subcategory createSubcategory(Subcategory subcategory) { return subcategoryRepository.save(subcategory); }

    public List<Subcategory> getSubcategories() { return subcategoryRepository.findAll(); }

    public Optional<Subcategory> readSubcategory(Integer id) {
        return subcategoryRepository.findById(id);
    }
    public void updateSubcategory(int subcategoryId, Subcategory newSubcategory) {
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).get();
        subcategory.setName(newSubcategory.getName());
        subcategory.setCategoryId(newSubcategory.getCategoryId());
        subcategoryRepository.save(subcategory);
    }
    public void deleteSubcategoryById(int id) { subcategoryRepository.deleteById(id); }
}
