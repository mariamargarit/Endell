package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AttributeValueDTO;
import dd.projects.ddshop.entities.AttributeValue;
import dd.projects.ddshop.mappers.AttributeValueMapper;
import dd.projects.ddshop.repos.AttributeValueRepository;
import dd.projects.ddshop.repos.ProductAttributeRepository;
import dd.projects.ddshop.validators.ProductAttributeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AttributeValueService {
    private final AttributeValueRepository attributeValueRepository;
    private final ProductAttributeRepository productAttributeRepository;
    private final AttributeValueMapper attributeValueMapper;
    private final ProductAttributeValidator productAttributeValidator;

    @Autowired
    public AttributeValueService (AttributeValueRepository attributeValueRepository, ProductAttributeRepository productAttributeRepository, AttributeValueMapper attributeValueMapper){
        this.attributeValueRepository = attributeValueRepository;
        this.productAttributeRepository = productAttributeRepository;
        this.attributeValueMapper = attributeValueMapper;
        this.productAttributeValidator = new ProductAttributeValidator(this.productAttributeRepository, attributeValueRepository);
    }

    public void createAttributeValue (AttributeValueDTO attributeValueDTO) {
        productAttributeValidator.validateAttributeValue(attributeValueDTO);
        AttributeValue attributeValue = new AttributeValue(attributeValueMapper.toAttributeValue(attributeValueDTO));
        attributeValueRepository.save(attributeValue);
    }

    public List<AttributeValueDTO> getAttributeValue() {
        return attributeValueRepository.findAll()
                .stream()
                .map(attributeValueMapper::toAttributeValueDTO)
                .collect(toList());
    }

    public void updateAttributeValue (int attributeValueId, AttributeValueDTO newAttributeValueDTO) {
        AttributeValue attributeValue = attributeValueRepository.findById(attributeValueId).get();
        attributeValue.setVal(newAttributeValueDTO.getVal());
        attributeValueRepository.save(attributeValue);
    }
    public void deleteAttributeValueById (int id) {
        attributeValueRepository.deleteById(id);
    }
}
