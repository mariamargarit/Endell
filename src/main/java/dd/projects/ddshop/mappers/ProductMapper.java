package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.BrandDTO;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.Brand;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Subcategory;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    @Mappings({
            @Mapping(target = "subcategoryId", qualifiedByName = "mappingSubcategory")
    })
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
    @Named(value = "mappingSubcategory")
    default SubcategoryDTO toSubcategoryDTO(Subcategory subcategory){
        if ( subcategory == null ) {
            return null;
        }
        return new SubcategoryDTO(subcategory.getId(),subcategory.getName(),subcategory.getCategoryId().getName());
    }
    BrandDTO toBrandDTO(Brand brand);
}
