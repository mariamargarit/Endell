package dd.projects.ddshop.validators;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.entities.User;
import dd.projects.ddshop.exceptions.AlreadyExistsException;
import dd.projects.ddshop.exceptions.InvalidInputException;
import dd.projects.ddshop.repos.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

public class UserValidator {
    private final UserRepository userRepository;
    private final MessageSource messageSource;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.messageSource = new AppConfiguration().messageSource();
    }

    public void validateUser(final UserCreationDTO userCreationDTO) {
        if(userCreationDTO.getFirstName().isEmpty() || userCreationDTO.getLastName().isEmpty() ||
                userCreationDTO.getEmail().isEmpty() || userCreationDTO.getPhoneNumber().isEmpty() || userCreationDTO.getPassword().isEmpty()) {
            throw new InvalidInputException(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
        }
        for(final User user : userRepository.findAll()){
            if(user.getEmail().equals(userCreationDTO.getEmail()))
                throw new AlreadyExistsException(messageSource.getMessage("api.error.user.email", null, Locale.ENGLISH));
        }
        if (!userCreationDTO.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new InvalidInputException(messageSource.getMessage("api.error.email", null, Locale.ENGLISH));
        }
        if(userCreationDTO.getPhoneNumber().length() != 10) {
            throw new InvalidInputException(messageSource.getMessage("api.error.user.phone", null, Locale.ENGLISH));
        }
        if (userCreationDTO.getPassword().length() < 8) {
            throw new InvalidInputException(messageSource.getMessage("api.error.password.length", null, Locale.ENGLISH));
        }
        if (!userCreationDTO.getPassword().matches("(.*[A-Z].*)")) {
            throw new InvalidInputException(messageSource.getMessage("api.error.password.upper", null, Locale.ENGLISH));
        }
        if (!userCreationDTO.getPassword().matches("(.*[a-z].*)")) {
            throw new InvalidInputException(messageSource.getMessage("api.error.password.lower", null, Locale.ENGLISH));
        }
        if (!userCreationDTO.getPassword().matches("(.*[0-9].*)")) {
            throw new InvalidInputException(messageSource.getMessage("api.error.password.number", null, Locale.ENGLISH));
        }
        if (!userCreationDTO.getPassword().matches("(.*[@,#,$,%].*$)")) {
            throw new InvalidInputException(messageSource.getMessage("api.error.password.special", null, Locale.ENGLISH));
        }
    }
}
