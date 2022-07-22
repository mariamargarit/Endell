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
public class AssignedValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_value_id")
    private AttributeValue attributeValueId;

    @OneToOne
    @JoinColumn(name = "product_attribute_id")
    private ProductAttribute productAttributeId;

    @ManyToMany(mappedBy = "assignedValueDTOList")
    private List<Variant> variantCombinations;

    public AssignedValue(AttributeValue value, ProductAttribute productAttribute) {
        this.attributeValueId = value;
        this.productAttributeId = productAttribute;
        this.variantCombinations = new ArrayList<>();
    }

}
