package dd.projects.ddshop.controllers;

import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    void create(Cart cart) {
        cartService.createCart(cart);
    }

    @GetMapping
    @ResponseBody
    List<Cart> read() {
        return cartService.getCarts();
    }

    @PutMapping
    @ResponseBody
    Cart update(Cart cart) {
        return cartService.updateCart(cart);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Integer id) {
        cartService.deleteCartById(id);
    }
}
