package dd.projects.ddshop.dtos;

import dd.projects.ddshop.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {
    public UserDTO(User user) {
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPhoneNumber(user.getPhoneNumber());
    }

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

}
