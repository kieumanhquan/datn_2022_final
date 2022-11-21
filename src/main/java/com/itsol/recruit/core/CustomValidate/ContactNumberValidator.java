package com.itsol.recruit.core.CustomValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements
        ConstraintValidator<ContactNumberConstraint, String> {

    @Override
    public void initialize(ContactNumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        if(contactField == null){
            return true;
        }
        return contactField.matches("[0-9]*")
                && (contactField.length() > 8) && (contactField.length() < 14);
    }

}
