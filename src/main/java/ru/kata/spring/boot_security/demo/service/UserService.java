package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    @Transactional
    public void add(User user);

    public void add(User user, Set<Role> roles);
    @Transactional
    public void removeUser(User user);

    public List<User> getUsers();

    public User findUser(Long id);

    @Transactional
    public void updateUser(User user);
}
