package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.entities.User;
import dd.projects.ddshop.mappers.UserCreationMapper;
import dd.projects.ddshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    UserCreationMapper userCreationMapper = new UserCreationMapper();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    ResponseEntity<Object> create(@RequestBody UserCreationDTO userCreationDTO) {
        userService.createUser(userCreationMapper.sourceToDestination(userCreationDTO));
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    ResponseEntity<List<UserDTO>> read() {
        List<UserDTO> userDTOS = userService.getUsers();
        return new ResponseEntity<>(userDTOS, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateUser/{id}")
    ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteUser/{id}")
    void delete(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }
}
