package ru.kata.spring.boot_security.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "")
    public String getAdminPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("emptyUser", new User());


        return "/admin";
    }

    @PostMapping("/addUser")
    public String createUser(@ModelAttribute("emptyUser") User user,
                             @RequestParam(value = "checkedRoles") String[] selectResult) {
        for (String s : selectResult) {

            user.addRole(roleService.getRoleByName(s));
        }
        log.warn(Arrays.toString(selectResult));
        log.warn("22 " + roleService.getRoleByName(selectResult[0]));
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = new User();
        user.setId(id);
        userService.removeUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @ModelAttribute("emptyUser") User updatedUser,
                             @RequestParam(value = "userRolesSelector", required = false) String[] selectResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        User currentUser = userService.getUserById(id);
        if (passwordEncoder.matches(updatedUser.getPassword(), currentUser.getPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Новый пароль совпадает с текущим паролем. Изменения не были внесены");
            return "redirect:/admin";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "пароль поменялся");

            updatedUser.setPassword(updatedUser.getPassword());
        }
        for (String s : selectResult) {
            updatedUser.addRole(roleService.getRoleByName(s));

        }
        userService.updateUser(updatedUser);
        return "redirect:/admin";
    }

}

