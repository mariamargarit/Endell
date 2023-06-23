package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.VariantCreationDTO;
import dd.projects.ddshop.dtos.VariantDTO;
import dd.projects.ddshop.entities.AssignedValue;
import dd.projects.ddshop.entities.Variant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VariantMapper {
    VariantDTO toVariantDTO(Variant variant);
    Variant toVariant(VariantDTO variantDTO);

    @Mappings({
            @Mapping(target = "assignedValueDTOList", expression = "java(toModel(variantCreateDTO.getAssignedValueDTOList()))")
    })
    Variant toModel(VariantCreationDTO variantCreateDTO);
    default AssignedValue toModel(final int attribute){
        final AssignedValue a = new AssignedValue();
        a.setId(attribute);
        return a;
    }

    List<AssignedValue> toModel(List<Integer> attributes);
}
