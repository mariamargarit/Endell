package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartEntryDTO {
    private int quantity;
    private float pricePerPiece;
    private float totalPricePerEntry;
    private VariantDTO variantId;
    private CartDTO cartId;
}
