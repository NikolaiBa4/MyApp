package borderCollieClubBulgaria.web.annotations.userAnnotations.checkEmailExistence;

import borderCollieClubBulgaria.domain.entities.UserEntity;
import borderCollieClubBulgaria.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class CheckEmailExistenceValidator implements ConstraintValidator<CheckEmailExistence, Object> {

    private final UserService userService;

    private String message;

    private String email;

    public CheckEmailExistenceValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(CheckEmailExistence constraintAnnotation) {
        this.email = constraintAnnotation.email();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object emailValue = beanWrapper.getPropertyValue(this.email);


        UserEntity user = this.userService.findByEmail(emailValue.toString());


        if (user.getEmail() == null) {
            return true;
        }
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).
                addPropertyNode(email)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();


        return false;
    }
}
