package borderCollieClubBulgaria.web.annotations.dogAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = CheckDogExistenceValidator.class)
public @interface CheckDogExistence {

    String name();

    String message() default "Valid dog name is required.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
