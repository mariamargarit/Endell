package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AttributeValueDTO;
import dd.projects.ddshop.entities.AttributeValue;
import dd.projects.ddshop.entities.ProductAttribute;
import dd.projects.ddshop.mappers.AttributeValueMapperImpl;
import dd.projects.ddshop.repos.AttributeValueRepository;
import dd.projects.ddshop.repos.ProductAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AttributeValueService {
    private final AttributeValueRepository attributeValueRepository;
    private final AttributeValueMapperImpl attributeValueMapper;
    private final ProductAttributeRepository productAttributeRepository;

    @Autowired
    public AttributeValueService (AttributeValueRepository attributeValueRepository, AttributeValueMapperImpl attributeValueMapper, ProductAttributeRepository productAttributeRepository){
        this.attributeValueRepository = attributeValueRepository;
        this.attributeValueMapper = attributeValueMapper;
        this.productAttributeRepository = productAttributeRepository;
    }

    public void createAttributeValue (AttributeValueDTO attributeValueDTO, Integer id) {
        final ProductAttribute productAttribute = productAttributeRepository.getReferenceById(id);
        AttributeValue attributeValue = new AttributeValue(attributeValueMapper.toAttributeValue(attributeValueDTO), productAttribute);
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
