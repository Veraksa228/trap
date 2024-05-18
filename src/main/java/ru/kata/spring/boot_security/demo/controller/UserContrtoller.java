package ru.kata.spring.boot_security.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@Slf4j
public class UserContrtoller {
private final UserService userService;
@Autowired
    public UserContrtoller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = new User();
        user.setLogin(userDetails.getUsername());
        model.addAttribute("user", user);
        return "user";
    }
}
