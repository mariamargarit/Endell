package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserOrderDTO {
    private String paymentType;
    private AddressDTO deliveryAddress;
    private AddressDTO invoiceAddress;
    private UserDTO userId;
    private CartDTO cartId;
    private Boolean valid;
}
