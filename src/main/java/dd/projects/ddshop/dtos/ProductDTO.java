package dd.projects.ddshop.dtos;

import dd.projects.ddshop.entities.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDTO {

    private int id;

    private String name;

    private SubcategoryDTO subcategoryId;

    private BrandDTO brand;

    private String image;

    private float price;

}
