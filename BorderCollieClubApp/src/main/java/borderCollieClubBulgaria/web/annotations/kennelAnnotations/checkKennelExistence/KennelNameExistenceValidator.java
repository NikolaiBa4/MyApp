package borderCollieClubBulgaria.web.annotations.kennelAnnotations.checkKennelExistence;

import borderCollieClubBulgaria.domain.entities.KennelEntity;
import borderCollieClubBulgaria.service.KennelService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class KennelNameExistenceValidator implements ConstraintValidator<KennelNameExistence, Object> {

    String name;

    String message;

    private final KennelService kennelService;

    public KennelNameExistenceValidator(KennelService kennelService) {

        this.kennelService = kennelService;

    }

    @Override
    public void initialize(KennelNameExistence constraintAnnotation) {

        this.name = constraintAnnotation.name();
        this.message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object nameValue = beanWrapper.getPropertyValue(this.name);

        KennelEntity kennel = this.kennelService.findKennelByName(nameValue.toString());

        if (kennel.getName() == null) {
            return true;
        }

        constraintValidatorContext.buildConstraintViolationWithTemplate(message).
                addPropertyNode(name).
                addConstraintViolation().
                disableDefaultConstraintViolation();

        return false;
    }
}
