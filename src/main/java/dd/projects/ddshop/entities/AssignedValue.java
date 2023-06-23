package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="assigned_value")
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

    @ManyToMany(mappedBy = "assignedValueDTOList", cascade = CascadeType.MERGE)
    private List<Variant> variantCombinations;

    public AssignedValue(AttributeValue value, ProductAttribute productAttribute) {
        this.attributeValueId = value;
        this.productAttributeId = productAttribute;
        this.variantCombinations = new ArrayList<>();
    }

}
