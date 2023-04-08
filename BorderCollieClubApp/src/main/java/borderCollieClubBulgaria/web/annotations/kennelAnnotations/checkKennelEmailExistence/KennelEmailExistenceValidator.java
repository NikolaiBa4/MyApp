package borderCollieClubBulgaria.web.annotations.kennelAnnotations.checkKennelEmailExistence;

import borderCollieClubBulgaria.domain.entities.KennelEntity;
import borderCollieClubBulgaria.service.KennelService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class KennelEmailExistenceValidator implements ConstraintValidator<KennelEmailExistence, Object> {

    String email;

    String message;

    private final KennelService kennelService;

    public KennelEmailExistenceValidator(KennelService kennelService) {
        this.kennelService = kennelService;
    }

    @Override
    public void initialize(KennelEmailExistence constraintAnnotation) {
        this.email = constraintAnnotation.email();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object emailValue = beanWrapper.getPropertyValue(this.email);

        KennelEntity kennel = this.kennelService.findKennelByEmail(emailValue.toString());

        if (kennel.getEmail() == null) {
            return true;
        }
        constraintValidatorContext.
                buildConstraintViolationWithTemplate(message).
                addPropertyNode(email).
                addConstraintViolation().
                disableDefaultConstraintViolation();

        return false;
    }
}
