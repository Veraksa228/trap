package ru.kata.spring.boot_security.demo.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(User user) {
        entityManager.persist(user);

    }

    @Override
    public User getUserById(long id) {
        return entityManager.createQuery("SELECT u from User u where u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void add(User user, Set<Role> roles) {
        user.setRoles(roles);
        entityManager.merge(user);
    }

    @Override
    public void removeUser(User user) {
        User managedUser = entityManager.merge(user);
        entityManager.remove(managedUser);
        log.info("remove user");
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u ", User.class).getResultList();
    }


    @Override
    public User getUserByEmail(String name) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();

        return user;
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
