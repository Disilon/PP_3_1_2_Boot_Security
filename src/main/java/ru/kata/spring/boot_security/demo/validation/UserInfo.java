package ru.kata.spring.boot_security.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserValidator.class)
@Target(ElementType.TYPE)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface UserInfo {
    String message() default "User with this username already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
