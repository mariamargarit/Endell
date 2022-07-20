package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.CartDTO;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.User;
import dd.projects.ddshop.services.CartService;
import dd.projects.ddshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("/createCart")
    public ResponseEntity<Object> create(@RequestBody CartDTO cartDTO) {
        User user = userService.readUser(cartDTO.getUserId());
        cartService.createCart(cartDTO, user);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllCarts")
    public ResponseEntity<List<CartDTO>> read() {
        return new ResponseEntity<>(cartService.getCarts(), HttpStatus.OK);
    }

    @PutMapping("/updateCart/{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody Cart newCart) {
        cartService.updateCart(id,newCart);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @DeleteMapping("/deleteCart/{id}")
    void delete (@PathVariable Integer id) {
        cartService.deleteCart(id);
    }
}
