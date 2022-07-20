package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SubcategoryMapper {
    SubcategoryDTO toSubcategoryDTO(Subcategory subcategory);
    Subcategory toSubcategory(SubcategoryDTO subcategoryDTO);
}
