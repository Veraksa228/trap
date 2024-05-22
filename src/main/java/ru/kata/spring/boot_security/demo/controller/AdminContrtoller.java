package ru.kata.spring.boot_security.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminContrtoller {
    private final UserService userService;

    @Autowired
    public AdminContrtoller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/*")
    public String adminStartPage() {
        return "admin-page";
    }

    @GetMapping()
    public String showAdmin() {
        return "admin-page";
    }

    @GetMapping("/show-user")
    public String showUser(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("newUser", new User());
        return "admin-page-showusers";
    }

    @PostMapping("/new-user")
    public String newUser(@ModelAttribute("user") User user) {
        log.info(user.getPassword());
        userService.add(user);
        return "redirect:/admin/show-user";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam("userId") Long id) {
        User user = userService.findUser(id);
        userService.removeUser(user);
        return "redirect:/admin/show-user";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("userId") Long userId, Model model) {
        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {

        userService.updateUser(user);
        return "redirect:/admin/show-user";
    }

}

