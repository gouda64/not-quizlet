package com.gouda.notquizlet.validator;

import com.gouda.notquizlet.entity.RegisteringUser;
import com.gouda.notquizlet.entity.User;
import com.gouda.notquizlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignUpValidator implements Validator {
    private final UserService userService;

    @Autowired
    public SignUpValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisteringUser user = (RegisteringUser) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.register.username_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.register.password_empty");

        if (!user.getUsername().matches("\\S+")) {
            errors.rejectValue("username", "error.register.username_spaces");
        }
        if (user.getUsername().length() < 4) {
            errors.rejectValue("username", "error.register.username_min");
        }
        if(user.getUsername().length() > 32){
            errors.rejectValue("username","error.register.username_max");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "error.register.username_unique");
        }
        if (userService.findByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "error.register.email_unique");
        }
        if (!user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.rejectValue("email", "error.register.email_valid");
        }

        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "error.register.password_min");
        }
        if (user.getPassword().length() > 32){
            errors.rejectValue("password", "error.register.password_max");
        }

        if (!user.getPasswordMatching().equals(user.getPassword())) {
            errors.rejectValue("passwordMatching", "error.register.password_match");
        }
    }
}
