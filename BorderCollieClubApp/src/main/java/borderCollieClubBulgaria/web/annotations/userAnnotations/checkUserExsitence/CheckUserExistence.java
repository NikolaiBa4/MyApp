package borderCollieClubBulgaria.web.annotations.userAnnotations.checkUserExsitence;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UserExistenceValidator.class)
public @interface CheckUserExistence {

    String username();

    String message() default "Valid username is required.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

