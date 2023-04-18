package borderCollieClubBulgaria.web.annotations.kennelAnnotations.checkKennelExistence;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = KennelNameExistenceValidator.class)
public @interface KennelNameExistence {

    String name();

    String message() default "Valid kennel name is required.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
