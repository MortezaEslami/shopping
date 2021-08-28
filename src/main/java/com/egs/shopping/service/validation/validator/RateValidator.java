package com.egs.shopping.service.validation.validator;

import com.egs.shopping.repository.UserRepository;
import com.egs.shopping.service.validation.Rate;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class RateValidator implements ConstraintValidator<Rate, Integer> {

    private final UserRepository repository;

    @Override
    public void initialize(Rate rate) {
    }

    @Override
    public boolean isValid(Integer rate, ConstraintValidatorContext cxt) {
        if (rate < 1 || rate > 5) {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate("{rate.not.valid}").addConstraintViolation();
            return false;
        }
        return true;
    }
}