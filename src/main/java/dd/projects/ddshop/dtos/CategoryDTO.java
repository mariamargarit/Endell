package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryDTO {
    private String name;
    private List<SubcategoryDTO> subcategoryDTOList;
}
