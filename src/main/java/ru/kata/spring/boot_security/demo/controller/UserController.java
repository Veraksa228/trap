package ru.kata.spring.boot_security.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entities.User;

@Controller
@Slf4j
public class UserController {


    @GetMapping("/user")
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = new User();
        user.setLogin(userDetails.getUsername());
        model.addAttribute("user", user);
        return "user";
    }
    @GetMapping("/")
    public String startPage(){
        return "redirect:/login";
    }
}
