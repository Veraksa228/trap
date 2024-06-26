package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    void add(User user);

    User getUserById(long id);

    void removeUser(User user);

    List<User> getAllUsers();

    User getUserByEmail(String name);

    void updateUser(User updatedUser);

    void add(User user, Set<Role> roles);

}

