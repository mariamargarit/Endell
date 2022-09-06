package dd.projects.ddshop.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float totalPrice;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToMany(mappedBy = "cartId")
    private List<CartEntry> cartEntry;

    private Boolean valid;

    public Cart(User user) {
        this.userId = user;
        this.cartEntry=new ArrayList<>();
        this.totalPrice = 0;
        this.valid = true;
    }
}
