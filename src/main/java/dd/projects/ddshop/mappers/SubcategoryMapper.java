package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.entities.Subcategory;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryMapper {
    public SubcategoryDTO trans(Subcategory subcategory) {
        return new SubcategoryDTO(subcategory.getName(), subcategory.getCategoryId().getId());
    }

}
