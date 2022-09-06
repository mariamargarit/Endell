package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductAttributeDTO {

    private int id;
    private String name;
    private List<AttributeValueDTO> attributeValues;
    private List <SubcategoryDTO> subcategories;

}
