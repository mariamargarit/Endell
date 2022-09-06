package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.*;
import dd.projects.ddshop.mappers.UserCreationMapperImpl;
import dd.projects.ddshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {
    private final UserService userService;
    private final UserCreationMapperImpl userCreationMapper;
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtils jwtUtils;
    @Autowired
    public UserController(UserService userService, UserCreationMapperImpl userCreationMapper) {
        this.userService = userService;
        this.userCreationMapper = userCreationMapper;
    }

//    @PostMapping("/auth/login")
//    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserRoleDTO userDetails = (UserRoleDTO) authentication.getPrincipal();
//
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getFirstName(),
//                userDetails.getLastName(),
//                userDetails.getEmail(),
//                userDetails.getPhoneNumber(),
//                userDetails.getRole()
//                ));
//    }

    @PostMapping("/auth/login")
    ResponseEntity<Object> login(@RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<>(userService.userLogin(userLoginDTO),HttpStatus.OK);
    }

    @PostMapping("/auth/signup")
    ResponseEntity<Object> create(@RequestBody UserCreationDTO userCreationDTO) {
        userService.createUser(userCreationDTO);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    ResponseEntity<List<UserDTO>> read() {
        List<UserDTO> userDTOS = userService.getUsers();
        return new ResponseEntity<>(userDTOS, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateUser/{id}")
    ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteUser/{id}")
    void delete(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }
}
