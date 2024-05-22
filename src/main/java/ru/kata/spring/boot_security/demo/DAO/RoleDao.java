package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.Set;

public interface RoleDao {
    void addRole(Role role);
    Set<Role> getRoles();
}
