package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AttributeValueDTO;
import dd.projects.ddshop.dtos.ProductAttributeDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.AssignedValue;
import dd.projects.ddshop.entities.AttributeValue;
import dd.projects.ddshop.entities.ProductAttribute;
import dd.projects.ddshop.mappers.ProductAttributeMapperImpl;
import dd.projects.ddshop.repos.AssignedValueRepository;
import dd.projects.ddshop.repos.ProductAttributeRepository;
import dd.projects.ddshop.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductAttributeService {
    private final ProductAttributeRepository productAttributeRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final AssignedValueRepository assignedValueRepository;
    private final ProductAttributeMapperImpl productAttributeMapper;

    @Autowired
    public ProductAttributeService(ProductAttributeMapperImpl productAttributeMapper, ProductAttributeRepository productAttributeRepository, SubcategoryRepository subcategoryRepository, AssignedValueRepository assignedValueRepository) {
        this.productAttributeRepository = productAttributeRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.assignedValueRepository = assignedValueRepository;
        this.productAttributeMapper = productAttributeMapper;
    }

    public void createProductAttribute(ProductAttributeDTO productAttributeDTO) {
        ProductAttribute productAttribute = new ProductAttribute();
        for(AttributeValueDTO attribute: productAttributeDTO.getAttributeValue())
            productAttribute.getAttributeValues().add(new AttributeValue(attribute.getVal(), productAttribute));

        for(SubcategoryDTO id : productAttributeDTO.getSubcategories())
            productAttribute.getSubcategories().add(subcategoryRepository.getReferenceById(id.getSubcategoryId()));

        productAttributeRepository.save(productAttribute);

        for (AttributeValue value : productAttribute.getAttributeValues())
            assignedValueRepository.save(new AssignedValue(value,productAttribute));
        productAttributeRepository.save(productAttribute);
    }
    public ProductAttribute readProductAttribute(Integer id) { return productAttributeRepository.getReferenceById(id); }
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
