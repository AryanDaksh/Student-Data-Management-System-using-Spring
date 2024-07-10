package student.studenthashmap.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeLimitValidator.class)
public @interface AgeLimit {
    int minimumAge() default 18;
    String message() default "Student must be atleast 18 years old.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
