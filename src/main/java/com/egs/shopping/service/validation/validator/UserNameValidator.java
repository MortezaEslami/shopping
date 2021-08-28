package com.egs.shopping.service.validation.validator;

import com.egs.shopping.model.User;
import com.egs.shopping.repository.UserRepository;
import com.egs.shopping.service.validation.UserName;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class UserNameValidator implements ConstraintValidator<UserName, String> {

    private final UserRepository repository;

    @Override
    public void initialize(UserName userName) {
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext cxt) {
        Optional<User> oneByUserName = repository.findOneByUserName(userName);
        if (oneByUserName.isPresent()) {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate("{username.exist}").addConstraintViolation();
            return false;
        }
        return true;
    }
}