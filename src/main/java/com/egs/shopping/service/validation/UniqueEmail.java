package com.egs.shopping.service.validation;

import com.egs.shopping.service.validation.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueEmailValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Email exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}