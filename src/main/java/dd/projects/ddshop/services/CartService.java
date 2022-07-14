package dd.projects.ddshop.services;

import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.repos.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart createCart(Cart cart) { return cartRepository.save(cart); }
    public List<Cart> getCarts() { return cartRepository.findAll(); }
    public Cart updateCart(Cart cart) { return cartRepository.save(cart); }
    public void deleteCartById(int id) { cartRepository.deleteById(id); }
}
