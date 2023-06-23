package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddressDTO {
    private int id;

    private String streetLine;

    private String postalCode;

    private String city;

    private String country;

    private String county;
}
