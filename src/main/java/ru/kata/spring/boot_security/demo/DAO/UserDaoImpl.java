package ru.kata.spring.boot_security.demo.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
        log.info("Add user");
    }

    @Override
    public void removeUser(User user) {
        User managedUser = entityManager.merge(user);
        entityManager.remove(managedUser);
        log.info("remove user");
    }

    @Override

    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u ", User.class).getResultList();
    }

    @Override
    public User findUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUser(String name) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
        log.info("user " + user.getId());
        return user;
    }

    @Override
    public void updateUser(User updatedUser) {
        entityManager.merge(updatedUser);
    }
}
