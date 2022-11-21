package com.itsol.recruit.core.CustomValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DueDateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DueDateConstraint {
    String message() default "Invalid due date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
