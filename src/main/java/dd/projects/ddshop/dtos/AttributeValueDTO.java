package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttributeValueDTO {

    private String val;

    private ProductAttributeDTO productAttributeId;
}