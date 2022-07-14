package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "productAttributeId")
    private List<AttributeValue> attributeValues;

    @ManyToMany(mappedBy = "productAttributes")
    private List<Subcategory> subcategories;

}
