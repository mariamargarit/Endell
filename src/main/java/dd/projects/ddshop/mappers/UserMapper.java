package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.nio.file.attribute.UserDefinedFileAttributeView;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
}
