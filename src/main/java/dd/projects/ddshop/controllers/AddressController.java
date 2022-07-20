package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.entities.Address;
import dd.projects.ddshop.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/createAddress")
    ResponseEntity<Object> create(@RequestBody AddressDTO addressDTO) {
        addressService.createAddress(addressDTO);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllAddresses")
    ResponseEntity<List<AddressDTO>> read() {
        return new ResponseEntity<>(addressService.getAddresses(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateAddress/{id}")
    ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody AddressDTO addressDTO) {
        addressService.updateAddress(id, addressDTO);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteAddress/{id}")
    void delete(@PathVariable Integer id) {
        addressService.deleteAddressById(id);
    }
}
