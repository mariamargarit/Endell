package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.entities.User;
import org.mapstruct.Mapper;

@Mapper
public class UserMapper {
    public User sourceToDestination(UserDTO userDTO) {
        if(userDTO == null){
            return null;
        }
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

}
