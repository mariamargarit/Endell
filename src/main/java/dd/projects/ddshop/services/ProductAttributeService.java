package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.ProductAttributeDTO;
import dd.projects.ddshop.entities.AssignedValue;
import dd.projects.ddshop.entities.AttributeValue;
import dd.projects.ddshop.entities.ProductAttribute;
import dd.projects.ddshop.mappers.ProductAttributeMapper;
import dd.projects.ddshop.repos.AssignedValueRepository;
import dd.projects.ddshop.repos.AttributeValueRepository;
import dd.projects.ddshop.repos.ProductAttributeRepository;
import dd.projects.ddshop.repos.SubcategoryRepository;
import dd.projects.ddshop.validators.ProductAttributeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductAttributeService {
    private final ProductAttributeRepository productAttributeRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final AssignedValueRepository assignedValueRepository;
    private final ProductAttributeMapper productAttributeMapper;
    private final ProductAttributeValidator productAttributeValidator;
    private final AttributeValueRepository attributeValueRepository;

    @Autowired
    public ProductAttributeService(ProductAttributeMapper productAttributeMapper, ProductAttributeRepository productAttributeRepository, SubcategoryRepository subcategoryRepository, AssignedValueRepository assignedValueRepository, AttributeValueRepository attributeValueRepository) {
        this.productAttributeRepository = productAttributeRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.assignedValueRepository = assignedValueRepository;
        this.productAttributeMapper = productAttributeMapper;
        this.productAttributeValidator = new ProductAttributeValidator(productAttributeRepository, attributeValueRepository);
        this.attributeValueRepository = attributeValueRepository;
    }

    public void createProductAttribute(ProductAttributeDTO productAttributeDTO) {
        productAttributeValidator.validateProductAttribute(productAttributeDTO);
        productAttributeRepository.save(productAttributeMapper.toProductAttribute(productAttributeDTO));
    }

    public void addSubcategoriesToProductAttribute(Integer subcategoryId, Integer productAttributeId) {
        ProductAttribute productAttribute = productAttributeRepository.getReferenceById(productAttributeId);
        if(!productAttribute.getSubcategories().contains(subcategoryRepository.getReferenceById(subcategoryId))){
            productAttribute.getSubcategories().add(subcategoryRepository.getReferenceById(subcategoryId));
            productAttributeRepository.save(productAttribute);
        }
    }

    public void addValuesToProductAttribute(Integer valueId, Integer productAttributeId) {
        AttributeValue attributeValue = attributeValueRepository.getReferenceById(valueId);
        ProductAttribute productAttribute = productAttributeRepository.getReferenceById(productAttributeId);
        if(!productAttribute.getAttributeValues().contains(attributeValue)){
            productAttribute.getAttributeValues().add(attributeValue);
            attributeValue.setProductAttributeId(productAttribute);
            attributeValueRepository.save(attributeValue);
            assignedValueRepository.save(new AssignedValue(attributeValue, productAttribute));
            productAttributeRepository.save(productAttribute);
        }
    }

    public List<ProductAttributeDTO> getProductAttribute() {
        return productAttributeRepository.findAll()
                .stream()
                .map(productAttributeMapper::toProductAttributeDTO)
                .collect(toList());
    }

    public void updateProductAttribute(int id, ProductAttributeDTO newProductAttributeDTO) {
        ProductAttribute productAttribute = productAttributeRepository.findById(id).get();
        productAttribute.setName(newProductAttributeDTO.getName());
        productAttributeRepository.save(productAttribute);
    }
    public void deleteProductAttribute(int id) { productAttributeRepository.deleteById(id); }
}
