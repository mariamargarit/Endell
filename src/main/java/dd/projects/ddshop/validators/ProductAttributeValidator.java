package dd.projects.ddshop.validators;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.AttributeValueDTO;
import dd.projects.ddshop.dtos.ProductAttributeDTO;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.entities.AttributeValue;
import dd.projects.ddshop.entities.ProductAttribute;
import dd.projects.ddshop.exceptions.AlreadyExistsException;
import dd.projects.ddshop.exceptions.InvalidInputException;
import dd.projects.ddshop.repos.AttributeValueRepository;
import dd.projects.ddshop.repos.ProductAttributeRepository;
import dd.projects.ddshop.repos.ProductRepository;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class ProductAttributeValidator {
    private final ProductAttributeRepository productAttributeRepository;
    private final AttributeValueRepository attributeValueRepository;
    private final MessageSource messageSource;

    public ProductAttributeValidator(ProductAttributeRepository productAttributeRepository, AttributeValueRepository attributeValueRepository) {
        this.productAttributeRepository = productAttributeRepository;
        this.messageSource = new AppConfiguration().messageSource();
        this.attributeValueRepository = attributeValueRepository;
    }

    public void validateProductAttribute(ProductAttributeDTO productAttributeDTO){
        if(productAttributeDTO.getName().isEmpty()) {
            throw new InvalidInputException(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
        }
        for(final ProductAttribute productAttribute : productAttributeRepository.findAll()){
            if(productAttribute.getName().equals(productAttributeDTO.getName()))
                throw new AlreadyExistsException(messageSource.getMessage("api.error.productAttribute.already.exists", null, Locale.ENGLISH));
        }
    }

    public void validateAttributeValue(AttributeValueDTO attributeValueDTO){
        if(attributeValueDTO.getVal().isEmpty()) {
            throw new InvalidInputException(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
        }
        for(final AttributeValue attributeValue : attributeValueRepository.findAll()){
            if(attributeValue.getVal().equals(attributeValueDTO.getVal()))
                throw new AlreadyExistsException(messageSource.getMessage("api.error.productAttribute.already.exists", null, Locale.ENGLISH));
        }
    }

}
