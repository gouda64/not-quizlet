package com.gouda.notquizlet.validator;

import com.gouda.notquizlet.entity.User;
import com.gouda.notquizlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignUpValidator implements Validator {
    private final UserService userService;

    @Autowired
    public SignUpValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "error.register.username_size");
        }
        if (!user.getUsername().matches("^[a-zA-Z0-9._$-]+$")) {
            errors.rejectValue("username", "error.register.username_chars");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "error.register.username_unique");
        }

        if (userService.findByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "error.register.email_unique");
        }
        if (!user.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            errors.rejectValue("email", "error.register.email_valid");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 128) {
            errors.rejectValue("password", "error.register.password_size");
        }
    }
}
