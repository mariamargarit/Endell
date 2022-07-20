package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddressDTO {

    private String streetLine;

    private int postalCode;

    private String city;

    private String country;

    private String county;
}
