package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({("/")})
    public String login(Model model) {
        return "redirect:/login";
    }

    @GetMapping(value = "/user")
    public String getUserPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "/user";
    }
}

