package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AttributeValueDTO;
import dd.projects.ddshop.entities.AttributeValue;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AttributeValueMapper {
    AttributeValueDTO toAttributeValueDTO(AttributeValue attributeValue);
    AttributeValue toAttributeValue(AttributeValueDTO attributeValueDTO);
}
