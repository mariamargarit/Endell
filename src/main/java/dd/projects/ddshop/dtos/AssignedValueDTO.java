package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignedValueDTO {
    private int id;
    private AttributeValueDTO attributeValueId;
    private ProductAttributeVariantDTO productAttributeId;
}
