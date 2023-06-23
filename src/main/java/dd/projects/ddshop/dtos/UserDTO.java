package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private AddressDTO defaultDeliveryAddress;

    private AddressDTO defaultBillingAddress;

}
