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
        entityManager.merge(user);
        log.info("Add user ===" + user.toString() + "!!!!!!!! " + user.getRoles());
    }

    public void add(User user, Set<Role> roles) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setRoles(roles);
        entityManager.merge(newUser);
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
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String name) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();

        return user;
    }

    @Override
    public void updateUser(long id, User user) {
        User userToUpdate = findUserById(id);
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setRoles(user.getRoles());

        entityManager.merge(userToUpdate);
    }
}
