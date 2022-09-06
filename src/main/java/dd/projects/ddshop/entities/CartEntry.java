package dd.projects.ddshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public CartEntry(int quantity, float pricePerPiece, float totalPricePerEntry, Variant variantId, Cart cartId) {
        this.quantity = quantity;
        this.pricePerPiece = pricePerPiece;
        this.totalPricePerEntry = totalPricePerEntry;
        this.variantId = variantId;
        this.cartId = cartId;
    }
}
