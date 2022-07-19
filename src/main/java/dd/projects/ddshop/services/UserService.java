package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.entities.User;
import dd.projects.ddshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){ return userRepository.save(user); }
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User user : users) {
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }
    public void updateUser(int id, User newUser) {
        User user = userRepository.findById(id).get();
        user.setDefaultBillingAddress(newUser.getDefaultBillingAddress());
        user.setDefaultDeliveryAddress(newUser.getDefaultDeliveryAddress());
        user.setEmail(newUser.getEmail());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }
    public void deleteUserById(int id) { userRepository.deleteById(id); }
}
