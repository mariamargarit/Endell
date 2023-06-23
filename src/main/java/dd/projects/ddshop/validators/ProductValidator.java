package dd.projects.ddshop.validators;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.BrandDTO;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.exceptions.AlreadyExistsException;
import dd.projects.ddshop.exceptions.InvalidInputException;
import dd.projects.ddshop.repos.BrandRepository;
import dd.projects.ddshop.repos.ProductRepository;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class ProductValidator {
    private final ProductRepository productRepository;
    private final MessageSource messageSource;
    private final BrandRepository brandRepository;

    public ProductValidator(ProductRepository productRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.messageSource = new AppConfiguration().messageSource();
        this.brandRepository = brandRepository;
    }

    public void validateProduct(ProductDTO productDTO){
        if(productDTO.getName().isEmpty()) {
            throw new InvalidInputException(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
        }
        if(productRepository.findByName(productDTO.getName()) != null) {
            throw new AlreadyExistsException(messageSource.getMessage("api.error.product.already.exists", null, Locale.ENGLISH));
        }
    }

    public void validateBrand(BrandDTO brandDTO){
        if(brandDTO.getName().isEmpty()) {
            throw new InvalidInputException(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
        }
        if(brandRepository.findBrandByName(brandDTO.getName()) != null) {
            throw new AlreadyExistsException(messageSource.getMessage("api.error.product.already.exists", null, Locale.ENGLISH));
        }
    }
}
