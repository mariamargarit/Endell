package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.VariantDTO;
import dd.projects.ddshop.entities.Variant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VariantMapper {
    VariantDTO toVariantDTO(Variant variant);
    Variant toVariant(VariantDTO variantDTO);
}
