package dd.projects.ddshop.dtos;

import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDTO {
    public CartDTO(Cart cart) {
        this.setTotalPrice(cart.getTotalPrice());
        this.setUserId(cart.getUserId().getId());
    }
    private float totalPrice;
    private int userId;
}
