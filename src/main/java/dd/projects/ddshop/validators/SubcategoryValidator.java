package dd.projects.ddshop.validators;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.exceptions.AlreadyExistsException;
import dd.projects.ddshop.exceptions.InvalidInputException;
import dd.projects.ddshop.repos.CategoryRepository;
import dd.projects.ddshop.repos.SubcategoryRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

public class SubcategoryValidator {
    private final SubcategoryRepository subcategoryRepository;
    private final MessageSource messageSource;

    public SubcategoryValidator(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.messageSource = new AppConfiguration().messageSource();
    }
    public void validateSubcategory(String name, Integer id) {
        if(name.isEmpty()){
            throw new InvalidInputException(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
        }
        for(final Subcategory subcategory : subcategoryRepository.findAll()){
            if(subcategory.getName().equals(name) && subcategory.getCategoryId().getId() == id)
                throw new AlreadyExistsException(messageSource.getMessage("api.error.subcategory.already.exists", null, Locale.ENGLISH));
        }
    }
}
