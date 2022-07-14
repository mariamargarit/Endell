package dd.projects.ddshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddressDTO {

    private String street_line;

    private int postal_code;

    private String city;

    private String country;

    private String county;
}
