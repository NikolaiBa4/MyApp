package borderCollieClubBulgaria.web.annotations.userAnnotations.checkUserExsitence;

import borderCollieClubBulgaria.domain.entities.UserEntity;
import borderCollieClubBulgaria.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class UserExistenceValidator implements ConstraintValidator<CheckUserExistence, Object> {

    private final UserService userService;

    private String message;

    private String username;

    public UserExistenceValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(CheckUserExistence constraintAnnotation) {
        this.username = constraintAnnotation.username();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object usernameValue = beanWrapper.getPropertyValue(this.username);

        UserEntity user = this.userService.findByUsername(usernameValue.toString());


        if (user.getUsername() == null) {
            return true;
        }
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).
                addPropertyNode(username)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();


        return false;
    }


}
