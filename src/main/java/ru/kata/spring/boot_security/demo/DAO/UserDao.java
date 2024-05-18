package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserDao {
    public void add(User user);


    public void removeUser(User user);


    public List<User> getUsers();


    public User findUser(Long id);


    public User findUser(String name);


    public void updateUser(User updatedUser);

}

