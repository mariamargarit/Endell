package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AttributeValueDTO;
import dd.projects.ddshop.dtos.ProductAttributeDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.AttributeValue;
import dd.projects.ddshop.entities.ProductAttribute;
import dd.projects.ddshop.entities.Subcategory;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductAttributeMapper {
    @Mappings({
            @Mapping(target = "subcategories", qualifiedByName = "mappingName")
    })
    ProductAttributeDTO toProductAttributeDTO(ProductAttribute productAttribute);
    ProductAttribute toProductAttribute(ProductAttributeDTO productAttributeDTO);
    @Named(value = "mappingName")
    default List<SubcategoryDTO> toSubcategoryDTO(List<Subcategory> subcategories){
        if ( subcategories == null ) {
            return null;
        }

        List<SubcategoryDTO> list1 = new ArrayList<SubcategoryDTO>( subcategories.size() );
        for(Subcategory subcategory: subcategories) {
            list1.add( new SubcategoryDTO(subcategory.getId(),subcategory.getName(),subcategory.getCategoryId().getName()) );
        }

        return list1;
    }


}
