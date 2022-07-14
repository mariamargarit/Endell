package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CartEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    private float pricePerPiece;

    private float totalPricePerEntry;

    @OneToOne
    @JoinColumn(name = "variant_id")
    private Variant variantId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cartId;

}
