package com.egs.shopping.service.validation;

import com.egs.shopping.service.validation.validator.UserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UserNameValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {
    String message() default "User name exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}