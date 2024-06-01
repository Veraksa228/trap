package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User getUserById(long id);

    User getUserByEmail(String name);

    void add(User user);

    void add(User user, Set<Role> roles);

    void removeUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);
}
