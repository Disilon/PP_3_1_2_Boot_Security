package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByUsername(String username);

    void create(@Valid User user);

    void update(@Valid User user);

    List<User> findAll();

    void deleteById(Long id) throws EntityNotFoundException;
}
