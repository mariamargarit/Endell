package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CartDTO;
import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.entities.*;
import dd.projects.ddshop.repos.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService (CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }
    public static Cart getCartFromDTO(CartDTO cartDTO, User user) {
        Cart cart = new Cart();
        cart.setTotalPrice(cartDTO.getTotalPrice());
        cart.setUserId(user);
        return cart;
    }
    public void createCart(CartDTO cartDTO, User user){
        Cart cart = getCartFromDTO(cartDTO, user);
        cartRepository.save(cart);
    }
    public Cart readCart(Integer id) {
        return cartRepository.getReferenceById(id);
    }
    public List<CartDTO> getCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDTO> cartDTOS = new ArrayList<>();
        for(Cart cart : carts) {
            cartDTOS.add(new CartDTO(cart));
        }
        return cartDTOS;
    }
    public void updateCart(int id, Cart newCart) {
        Cart cart = cartRepository.findById(id).get();
        cart.setTotalPrice(newCart.getTotalPrice());
        cart.setUserId(newCart.getUserId());
        cartRepository.save(cart);
    }
    public void deleteCart(int id) { cartRepository.deleteById(id); }
}
