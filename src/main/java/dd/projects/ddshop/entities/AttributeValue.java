package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String val;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_attribute_id")
    private ProductAttribute productAttributeId;

    public AttributeValue(String attribute, ProductAttribute productAttribute) {
        this.val = attribute;
        this.productAttributeId = productAttribute;
    }
    public AttributeValue(AttributeValue attributeValue, ProductAttribute productAttribute) {
        this.val = attributeValue.getVal();
        this.productAttributeId = productAttribute;
    }

}
