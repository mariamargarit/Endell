package dd.projects.ddshop.entities;

import dd.projects.ddshop.dtos.UserOrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="user_order")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String paymentType;

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
    public UserOrder(UserOrderDTO orderDTO, Address address, User user, Cart cart) {
        this.id = orderDTO.getId();
        this.paymentType = orderDTO.getPaymentType();
        this.deliveryAddress = address;
        this.invoiceAddress = address;
        this.userId = user;
        this.cartId = cart;
    }
}
