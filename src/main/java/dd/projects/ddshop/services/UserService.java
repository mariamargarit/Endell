package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.dtos.UserLoginDTO;
import dd.projects.ddshop.dtos.UserRoleDTO;
import dd.projects.ddshop.entities.User;
import dd.projects.ddshop.enumerated.ERole;
import dd.projects.ddshop.exceptions.InvalidInputException;
import dd.projects.ddshop.mappers.AddressMapper;
import dd.projects.ddshop.mappers.UserCreationMapper;
import dd.projects.ddshop.mappers.UserMapper;
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
    private final UserCreationMapper userCreationMapper;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, UserCreationMapper userCreationMapper, AddressMapper addressMapper) {
        this.userRepository = userRepository;
        this.userCreationMapper = userCreationMapper;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.userValidator = new UserValidator(userRepository);
    }

//    @Transactional
//    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
//
//        return UserDetailsImpl.build(user);
//    }
    public UserRoleDTO userLogin (final UserLoginDTO userLoginDTO) {

        final User user = userRepository.findByEmail(userLoginDTO.getEmail());
        if(user==null){
            throw new InvalidInputException("This account does not exist");
        }
        if(!user.getPassword().equals(Password.getMD5EncryptedValue(userLoginDTO.getPassword())))
            throw new InvalidInputException("ERROR: Incorrect password");
        return new UserRoleDTO(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNumber(),user.getRole().name());
    }

    public User createUser(UserCreationDTO userCreationDTO){
        userValidator.validateUser(userCreationDTO);
        final User user = userMapper.toUser(userCreationDTO);
        user.setPassword(Password.getMD5EncryptedValue(user.getPassword()));
        user.setRole(ERole.user);
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
