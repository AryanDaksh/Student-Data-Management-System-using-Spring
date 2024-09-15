package student.studenthashmap.validation;

import java.lang.annotation.*;
import jakarta.validation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeLimitValidator.class)
public @interface AgeLimit {
    int minimumAge() default 18;
    String message() default "Student must be atleast 18 years old.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
