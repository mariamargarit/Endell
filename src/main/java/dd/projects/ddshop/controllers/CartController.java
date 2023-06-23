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
@CrossOrigin("http://localhost:4200")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("/createCart/{id}")
    public ResponseEntity<Object> create(@RequestBody CartDTO cartDTO, @PathVariable("id") int id) {
        cartService.createCart(cartDTO, id);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
    @GetMapping("/getAllCarts")
    public ResponseEntity<CartDTO> read(@RequestParam(name="email") String email) {
        return new ResponseEntity<>(cartService.getCarts(email), HttpStatus.OK);
    }
    @GetMapping("/getCurrentCart")
    public ResponseEntity<CartDTO> getCurrentCart(@RequestParam(name="email") String email) {
        return new ResponseEntity<>(cartService.getCurrentCart(email), HttpStatus.OK);
    }

    @PutMapping("/updateCart/{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody CartDTO newCartDTO) {
        cartService.updateCart(id,newCartDTO);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @PutMapping("/updateCartQuantity/{id}")
    public ResponseEntity<Object> updateCartQuantity (@PathVariable Integer id, @RequestBody Integer newQuantity) {
        cartService.updateCartQuantity(id,newQuantity);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @DeleteMapping("/deleteCart/{id}")
    void delete (@PathVariable Integer id) {
        cartService.deleteCart(id);
    }
}
