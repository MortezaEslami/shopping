package com.egs.shopping.service.validation;

import com.egs.shopping.service.validation.validator.RateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {RateValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Rate {
    String message() default "Rate is not acceptable";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}