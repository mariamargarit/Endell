package dd.projects.ddshop.dtos;

import dd.projects.ddshop.entities.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDTO {

    private int id;

    private String name;

    private String description;

    private SubcategoryDTO subcategoryId;

}
