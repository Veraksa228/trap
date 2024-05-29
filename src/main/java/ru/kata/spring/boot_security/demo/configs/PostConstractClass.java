package ru.kata.spring.boot_security.demo.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class PostConstractClass {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;

    public PostConstractClass(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void addAdminAndUser() {
        Role roleAdmin = new Role();
        roleAdmin.setRoleName("ADMIN");

        Role roleUser = new Role();
        roleUser.setRoleName("USER");

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        Set<Role> roles = new HashSet<>();
        roles.add(roleAdmin);
        roles.add(roleUser);

        Set<Role> rolesForUser = new HashSet<>();
        rolesForUser.add(roleUser);

        User admin = new User();
        admin.setEmail("admin@mail.ru");
        admin.setPassword("123");
        admin.setRoles(roles);

        User user = new User();
        user.setEmail("user@mail.ru");
        user.setPassword("123");
        user.setRoles(rolesForUser);
        userService.add(user, rolesForUser);
        userService.add(admin, roles);
    }
}






