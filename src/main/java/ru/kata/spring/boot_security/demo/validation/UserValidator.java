package ru.kata.spring.boot_security.demo.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;

@Component
public class UserValidator implements Validator {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public UserValidator(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (user.getRoles().isEmpty()) {
            errors.rejectValue("roles", "", "User should have at least one role");
        }

        try {
            userDetailsServiceImpl.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException e) {
            return;
        }
        errors.rejectValue("username", "", "User with this username already exists");
    }
}
