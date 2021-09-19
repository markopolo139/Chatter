package com.chatter.chatter.web.errorHandler.validators;

import com.chatter.core.values.RequestOptions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequestOptionValidatorImpl implements ConstraintValidator<RequestOptionValidator, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            RequestOptions.valueOf(value);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}
