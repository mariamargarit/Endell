package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserOrderDTO {
    private int id;
    private String paymentType;
    private AddressDTO deliveryAddress;
    private AddressDTO invoiceAddress;
    private Integer userId;
    private Integer cartId;
}
