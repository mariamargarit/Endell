package dd.projects.ddshop.validators;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.exceptions.AlreadyExistsException;
import dd.projects.ddshop.exceptions.InvalidInputException;
import dd.projects.ddshop.repos.ProductRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProductValidator {
    private final ProductRepository productRepository;
    private final MessageSource messageSource = new AppConfiguration().messageSource();

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validateProduct(ProductDTO productDTO){
        if(productDTO.getName().isEmpty() || productDTO.getDescription().isEmpty()) {
            throw new InvalidInputException(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
        }
        if(productRepository.findByName(productDTO.getName()) != null) {
            throw new AlreadyExistsException(messageSource.getMessage("api.error.product.already.exists", null, Locale.ENGLISH));
        }
    }
}
