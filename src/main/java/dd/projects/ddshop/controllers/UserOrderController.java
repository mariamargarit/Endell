package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.UserOrderDTO;
import dd.projects.ddshop.entities.Address;
import dd.projects.ddshop.entities.UserOrder;
import dd.projects.ddshop.services.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserOrderController {
    private final UserOrderService userOrderService;

    @Autowired
    public UserOrderController(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    @PostMapping("/createOrder")
    ResponseEntity<Object> create(@RequestBody UserOrderDTO order) {
        userOrderService.createOrder(order);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrders")
    ResponseEntity<List<UserOrder>>read() {
        return new ResponseEntity<>(userOrderService.getOrders(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateOrder/{id}")
    ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UserOrder order) {
        userOrderService.updateOrder(id, order);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Integer id) {
        userOrderService.deleteOrderById(id);
    }

}
