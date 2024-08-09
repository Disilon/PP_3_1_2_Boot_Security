package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.validation.UserValidator;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminAPI {
    private final UserValidator userValidator;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminAPI(UserValidator userValidator, UserService userService, RoleService roleService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "User with id " + id + " was deleted";
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }
}
