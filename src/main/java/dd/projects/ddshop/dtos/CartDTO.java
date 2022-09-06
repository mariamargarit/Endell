package dd.projects.ddshop.dtos;

import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.entities.CartEntry;
import dd.projects.ddshop.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartDTO {

    private int id;
    private float totalPrice;
    private Boolean valid;
    private List<CartEntryDTO> cartEntry;
    //private UserDTO userId;
}
