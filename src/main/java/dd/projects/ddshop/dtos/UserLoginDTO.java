package dd.projects.ddshop.dtos;

import dd.projects.ddshop.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}
