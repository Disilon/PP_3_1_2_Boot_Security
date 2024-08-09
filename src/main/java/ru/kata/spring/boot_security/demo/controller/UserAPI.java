package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserAPI {
    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public UserDto getUser(Principal principal) {
        return convertUserToDto(userService.findByUsername(principal.getName()));
    }

    private UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setRoles(user.getRoles());
        userDto.setAge(user.getAge());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }

}
