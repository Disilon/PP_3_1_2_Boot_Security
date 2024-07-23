package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.validation.UserValidator;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserValidator userValidator;
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UserController(UserValidator userValidator, UserService userService, RoleService roleService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/user")
    public String info(Principal principal, ModelMap model) {
        List<Role> roles = roleService.findAll();
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user";
    }
}
