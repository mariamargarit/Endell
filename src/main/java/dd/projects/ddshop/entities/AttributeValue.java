package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="attribute_value")
public class AttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String val;

    @ManyToOne
    @JoinColumn(name = "product_attribute_id")
    private ProductAttribute productAttributeId;

    @OneToOne(mappedBy = "attributeValueId", cascade = CascadeType.ALL)
    private AssignedValue assignedValue;

    public AttributeValue(String attribute, ProductAttribute productAttribute) {
        this.val = attribute;
        this.productAttributeId = productAttribute;
    }
    public AttributeValue(AttributeValue attributeValue) {
        this.val = attributeValue.getVal();
    }

}
