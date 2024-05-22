package ru.kata.spring.boot_security.demo.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    public Set<Role> getRoles() {
        log.error(entityManager.createQuery("SELECT r FROM Role r ",Role.class).getResultList().toString());
        return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r ",Role.class).getResultList());


    }
}
