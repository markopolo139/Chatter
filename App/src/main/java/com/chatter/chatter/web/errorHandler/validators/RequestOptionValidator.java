package com.chatter.chatter.web.errorHandler.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequestOptionValidatorImpl.class)
public @interface RequestOptionValidator {
    String message() default "Invalid request option";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
