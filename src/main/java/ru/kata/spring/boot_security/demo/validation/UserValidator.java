package ru.kata.spring.boot_security.demo.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UserValidator implements ConstraintValidator<UserInfo, User> {
        private final UserRepository userRepository;

        @Autowired
        public UserValidator(UserRepository userRepository) {
                this.userRepository = userRepository;
        }

        @Override
        public boolean isValid(User user, ConstraintValidatorContext context) {
                return !userRepository.existsById(user.getId());
        }
}
