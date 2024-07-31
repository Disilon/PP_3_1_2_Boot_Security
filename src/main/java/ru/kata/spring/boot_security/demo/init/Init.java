package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        User root;
        try {
            root = userService.findByUsername("root");
        } catch (Exception e) {
            root = null;
        }
        if (root == null) {
            userService.create(new User("root", "root",
                    "", "", 0, "", roleService.findAll()));
        }
    }
}
