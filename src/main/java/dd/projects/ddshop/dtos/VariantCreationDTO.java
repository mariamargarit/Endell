package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class VariantCreationDTO {
    private int id;

    private int availableQuantity;

    private Date addedDate;

    private ProductDTO productId;

    private List<Integer> assignedValueDTOList;
}
