package student.studenthashmap.validation;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeLimitValidator implements ConstraintValidator<AgeLimit, LocalDate>{

    int minimumAge;

    @Override
    public void initialize(AgeLimit constraintAnnotation){
        this.minimumAge = constraintAnnotation.minimumAge();
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext constraintValidatorContext) {
        if (birthDate == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate minimumAgeBefore = today.minusYears(this.minimumAge);
        return !minimumAgeBefore.isBefore(birthDate);
    }
    
}
