package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    @Transactional
    public void add(User user);


    @Transactional
    public void removeUser(User user);

    public List<User> getUsers();

    public User findUser(Long id);

    @Transactional
    public void updateUser(User user);
}
