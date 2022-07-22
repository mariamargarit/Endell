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
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "productAttributeId")
    private List<AttributeValue> attributeValues;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "subcategory_product_attribute",
            joinColumns = @JoinColumn(name = "product_attribute_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subcategory_id",
                    referencedColumnName = "id"))
    private List<Subcategory> subcategories;

    public ProductAttribute(String name) {
        this.name = name;
        this.attributeValues = new ArrayList<>();
        this.subcategories = new ArrayList<>();
    }
}
