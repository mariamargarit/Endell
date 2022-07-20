package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.entities.User;
import dd.projects.ddshop.mappers.AddressMapperImpl;
import dd.projects.ddshop.mappers.UserCreationMapperImpl;
import dd.projects.ddshop.mappers.UserMapper;
import dd.projects.ddshop.mappers.UserMapperImpl;
import dd.projects.ddshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserCreationMapperImpl userCreationMapper;
    private final UserMapperImpl userMapper;
    private final AddressMapperImpl addressMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapperImpl userMapper, UserCreationMapperImpl userCreationMapper, AddressMapperImpl addressMapper) {
        this.userRepository = userRepository;
        this.userCreationMapper = userCreationMapper;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    public User createUser(UserCreationDTO userCreationDTO){
        return userRepository.save(userCreationMapper.toUser(userCreationDTO));
    }
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDTO)
                .collect(toList());
    }
    public void updateUser(int id, UserDTO newUser) {
        User user = userRepository.findById(id).get();
        user.setDefaultBillingAddress(addressMapper.toAddress(newUser.getDefaultBillingAddress()));
        user.setDefaultDeliveryAddress(addressMapper.toAddress(newUser.getDefaultDeliveryAddress()));
        user.setEmail(newUser.getEmail());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setPhoneNumber(newUser.getPhoneNumber());
        userRepository.save(user);
    }
    public User readUser(Integer id) {
        return userRepository.getReferenceById(id);
    }
    public void deleteUserById(int id) { userRepository.deleteById(id); }
}
