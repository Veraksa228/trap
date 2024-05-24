package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    void add(User user);


    void removeUser(User user);


    List<User> getAllUsers();


    User findUserById(Long id);


    User getUserByEmail(String name);


    void updateUser(long id,User updatedUser);

    void add(User user, Set<Role> roles);

}

