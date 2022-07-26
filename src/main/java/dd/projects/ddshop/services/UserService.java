package dd.projects.ddshop.services;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.entities.User;
import dd.projects.ddshop.mappers.AddressMapperImpl;
import dd.projects.ddshop.mappers.UserCreationMapperImpl;
import dd.projects.ddshop.mappers.UserMapperImpl;
import dd.projects.ddshop.repos.UserRepository;
import dd.projects.ddshop.utils.Password;
import dd.projects.ddshop.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserCreationMapperImpl userCreationMapper;
    private final UserMapperImpl userMapper;
    private final AddressMapperImpl addressMapper;

    private final UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserMapperImpl userMapper, UserCreationMapperImpl userCreationMapper, AddressMapperImpl addressMapper) {
        this.userRepository = userRepository;
        this.userCreationMapper = userCreationMapper;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.userValidator = new UserValidator(userRepository);
    }

    public User createUser(UserCreationDTO userCreationDTO){
        userValidator.validateUser(userCreationDTO);
        final User user = userMapper.toUser(userCreationDTO);
        user.setPassword(Password.getMD5EncryptedValue(user.getPassword()));
        return userRepository.save(user);
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
