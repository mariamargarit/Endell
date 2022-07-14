package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dto.UserCreationDTO;
import dd.projects.ddshop.dto.UserDTO;
import dd.projects.ddshop.entities.User;
import org.mapstruct.Mapper;

@Mapper
public class UserCreationMapper {
    public UserCreationMapper() {
    }

    public User sourceToDestination(UserCreationDTO userCreationDTO) {
        if(userCreationDTO == null){
            return null;
        }
        User user = new User();
        user.setFirstName(userCreationDTO.getFirstName());
        user.setLastName(userCreationDTO.getLastName());
        user.setEmail(userCreationDTO.getEmail());
        user.setPhoneNumber(userCreationDTO.getPhoneNumber());
        return user;
    }
}
