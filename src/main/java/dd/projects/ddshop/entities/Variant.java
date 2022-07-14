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

    private int price;

    private int availableQuantity;

    @Temporal(TemporalType.DATE)
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @OneToMany(mappedBy = "variantId")
    private List<VariantCombination> variantCombinations;

}
