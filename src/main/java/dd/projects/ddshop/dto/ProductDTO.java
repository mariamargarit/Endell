package dd.projects.ddshop.dto;

import dd.projects.ddshop.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ProductDTO {

    private String name;

    private String description;

    private int subcategoryId;
}
