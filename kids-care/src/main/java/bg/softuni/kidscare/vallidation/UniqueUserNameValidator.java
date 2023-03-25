package bg.softuni.kidscare.vallidation;

import bg.softuni.kidscare.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
    private final UserRepository userRepository;

    @Autowired
    public UniqueUserNameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userRepository
                .findByUsername(value)
                .isEmpty();
    }
}
