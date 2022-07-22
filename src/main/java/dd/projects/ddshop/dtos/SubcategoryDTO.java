package dd.projects.ddshop.dtos;

import dd.projects.ddshop.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SubcategoryDTO {
    private int id;
    private String name;
}
