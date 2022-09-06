package dd.projects.ddshop.entities;

import dd.projects.ddshop.enumerated.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToOne
    @JoinColumn(name="default_delivery_address")
    private Address defaultDeliveryAddress;

    @OneToOne
    @JoinColumn(name="default_billing_address")
    private Address defaultBillingAddress;

    @OneToMany(mappedBy = "userId")
    private List<UserOrder> order;

}
