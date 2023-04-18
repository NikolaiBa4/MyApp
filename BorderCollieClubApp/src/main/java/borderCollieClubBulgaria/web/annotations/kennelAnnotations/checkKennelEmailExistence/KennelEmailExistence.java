package borderCollieClubBulgaria.web.annotations.kennelAnnotations.checkKennelEmailExistence;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = KennelEmailExistenceValidator.class)
public @interface KennelEmailExistence {

    String email();

    String message() default "Valid email is required.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
