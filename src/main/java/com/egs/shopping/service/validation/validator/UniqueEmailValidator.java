package com.egs.shopping.service.validation.validator;

import com.egs.shopping.model.User;
import com.egs.shopping.repository.UserRepository;
import com.egs.shopping.service.validation.UniqueEmail;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository repository;

    @Override
    public void initialize(UniqueEmail uniqueEmail) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        Optional<User> oneByEmail = repository.findOneByEmailIgnoreCase(email);
        if (oneByEmail.isPresent()) {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate("{email.exist}").addConstraintViolation();
            return false;
        }
        return true;
    }
}