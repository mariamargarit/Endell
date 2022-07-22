package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Var;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategoryId;

    @OneToMany(mappedBy = "productId")
    private List<Variant> variants;

    public Product(Product toProduct, Subcategory subcategory) {
        this.id = toProduct.getId();
        this.name = toProduct.getName();
        this.description = toProduct.getDescription();
        this.subcategoryId = subcategory;
    }
}
