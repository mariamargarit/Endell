package dd.projects.ddshop.dtos;

import dd.projects.ddshop.entities.Variant;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class VariantDTO {

    private float price;

    private int availableQuantity;

    private Date addedDate;

    private ProductDTO productId;

    private List<AssignedValueDTO> assignedValueDTOList;

}
