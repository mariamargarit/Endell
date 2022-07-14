package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class AssignedValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "attribute_value_id")
    private AttributeValue attributeValueId;

    @OneToOne
    @JoinColumn(name = "product_attribute_id")
    private ProductAttribute productAttributeId;

    @OneToMany(mappedBy = "assignedValueId")
    private List<VariantCombination> variantCombinations;

}
