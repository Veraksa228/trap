package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleDao {
    void addRole(Role role);
    List<Role> getAllRoles();
    Role getRoleByName(String name);
}
