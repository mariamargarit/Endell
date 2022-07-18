package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Var;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @OneToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategoryId;

    @OneToMany(mappedBy = "productId")
    private List<Variant> variants;

}
