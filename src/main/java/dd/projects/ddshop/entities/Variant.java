package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float price;

    private int availableQuantity;

    @Temporal(TemporalType.DATE)
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "variant_combination",
            joinColumns = @JoinColumn(name = "variant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "assigned_value_id",
                    referencedColumnName = "id"))
    private List<AssignedValue> variantCombinations;

}
