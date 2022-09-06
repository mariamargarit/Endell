package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.entities.*;
import dd.projects.ddshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class CartEntryController {
    private final CartEntryService cartEntryService;
    private final VariantService variantService;
    private final CartService cartService;

    @Autowired
    public CartEntryController(CartEntryService cartEntryService, VariantService variantService, CartService cartService) {
        this.cartEntryService = cartEntryService;
        this.variantService = variantService;
        this.cartService = cartService;
    }

    @PostMapping("/createCartEntry/{id}")
    public ResponseEntity<Object> create(@RequestBody CartEntryDTO cartEntryDTO, @PathVariable int id) {
        cartEntryService.createCartEntry(cartEntryDTO, id);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllCartEntries")
    public ResponseEntity<List<CartEntryDTO>> read() {
        return new ResponseEntity<>(cartEntryService.getCartEntry(), HttpStatus.OK);
    }

    @PutMapping("/updateCartEntry/{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody CartEntryDTO newCartEntryDTO) {
        cartEntryService.updateCartEntry(id,newCartEntryDTO);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @DeleteMapping("/deleteCartEntry/{id}")
    void delete (@PathVariable Integer id) {
        cartEntryService.deleteCartEntry(id);
    }
}
