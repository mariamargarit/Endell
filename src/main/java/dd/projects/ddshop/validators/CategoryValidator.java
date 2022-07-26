package dd.projects.ddshop.validators;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.exceptions.AlreadyExistsException;
import dd.projects.ddshop.exceptions.InvalidInputException;
import dd.projects.ddshop.repos.CategoryRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CategoryValidator {
    private final CategoryRepository categoryRepository;
    private final MessageSource messageSource;

    public CategoryValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.messageSource = new AppConfiguration().messageSource();
    }
    public void validateCategory(CategoryDTO categoryDTO) {
        if(categoryDTO.getName().isEmpty()){
            throw new InvalidInputException(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
        }
        for(final Category category : categoryRepository.findAll()){
            if(category.getName().equals(categoryDTO.getName()))
                throw new AlreadyExistsException(messageSource.getMessage("api.error.category.already.exists", null, Locale.ENGLISH));
        }
    }
}
