package borderCollieClubBulgaria.web.annotations.dogAnnotation;

import borderCollieClubBulgaria.domain.entities.DogEntity;
import borderCollieClubBulgaria.service.DogService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class CheckDogExistenceValidator implements ConstraintValidator<CheckDogExistence, Object> {


    private final DogService dogService;
    private String name;

    private String message;

    public CheckDogExistenceValidator(DogService dogService) {
        this.dogService = dogService;
    }


    @Override
    public void initialize(CheckDogExistence constraintAnnotation) {
        this.name = constraintAnnotation.name();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object dogNameValue = beanWrapper.getPropertyValue(this.name);


        DogEntity dog = this.dogService.findByDogName(dogNameValue.toString());


        if (dog.getName() == null) {
            return true;
        }
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).
                addPropertyNode(name)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();


        return false;
    }
}
