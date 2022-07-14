package dd.projects.ddshop.entities;

import dd.projects.ddshop.enumerated.PaymentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToOne
    @JoinColumn(name = "delivery_address")
    private Address deliveryAddress;

    @OneToOne
    @JoinColumn(name = "invoice_address")
    private Address invoiceAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cartId;
}
