package com.itsol.recruit.core.CustomValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DueDateValidator implements
        ConstraintValidator<DueDateConstraint, Date> {

    @Override
    public void initialize(DueDateConstraint contactNumber) {
    }

    @Override
    public boolean isValid(Date contactField,
                           ConstraintValidatorContext cxt) {
        Date currentDate = new Date();
        return currentDate.before(contactField);
    }

}
