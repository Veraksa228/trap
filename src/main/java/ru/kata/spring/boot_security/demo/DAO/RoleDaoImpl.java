package ru.kata.spring.boot_security.demo.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Slf4j
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r ", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }


}

