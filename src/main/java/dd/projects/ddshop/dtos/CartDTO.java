package dd.projects.ddshop.dtos;

import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDTO {

    private float totalPrice;
    private UserDTO userId;
}
