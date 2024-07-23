package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.validation.UserValidator;
import ru.kata.spring.boot_security.demo.validation.group.CreateAction;

import javax.validation.groups.Default;
import java.util.List;

@Controller
public class GuestController {
    private final UserValidator userValidator;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public GuestController(UserValidator userValidator, UserService userService, RoleService roleService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/register")
    public String addUser(@ModelAttribute("user") User user, ModelMap model) {
        List<Role> roles = roleService.findAll();
        if (user == null) user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "register";
    }

    @PostMapping(value = "/register")
    public String create(Authentication authentication, @ModelAttribute @Validated({Default.class, CreateAction.class}) User user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/register";
        }
        userService.create(user);
        if (authentication != null && authentication.getAuthorities().toString().contains("ADMIN")) {
            return "redirect:/admin/admin";
        } else {
            return "redirect:/login";
        }
    }
}
