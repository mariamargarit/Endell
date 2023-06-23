package dd.projects.ddshop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String streetLine;

    private String postalCode;

    private String city;

    private String county;

    private String country;

}
