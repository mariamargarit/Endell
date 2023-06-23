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
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategoryId;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private String image;

    private float price;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
    private List<Variant> variants;

    public Product(Product toProduct, Subcategory subcategory) {
        this.id = toProduct.getId();
        this.name = toProduct.getName();
        this.brand = toProduct.getBrand();
        this.image = toProduct.getImage();
        this.price = toProduct.getPrice();
        this.subcategoryId = subcategory;
    }
}
