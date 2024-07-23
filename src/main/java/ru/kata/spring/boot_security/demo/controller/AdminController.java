package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.validation.UserValidator;

import javax.validation.groups.Default;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserValidator userValidator;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserValidator userValidator, UserService userService, RoleService roleService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String listOfUsers(ModelMap model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/admin";
    }

    @GetMapping(value = "/edit")
    public String editUser(@RequestParam(name = "id") Long id, @ModelAttribute("user") User user, ModelMap model) {
        List<Role> roles = roleService.findAll();
        if (user.getUsername() == null) {
            user = userService.findById(id);
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "admin/edit";
    }

    @PostMapping(value = "/edit")
    public String update(@RequestParam(name = "id") Long id,
                         @ModelAttribute @Validated({Default.class}) User user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/admin/edit?id=" + id;
        }
        userService.save(user);
        return "redirect:/admin/admin";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam(name = "id") Long id) throws Exception {
        userService.deleteById(id);
        return "redirect:/admin/admin";
    }
}
