package ru.alishev.springcourse.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


@Component
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(@Autowired EntityManagerFactory factory) {
        entityManager = factory.createEntityManager();
    }


    @Override
    public List<User> getAllUsers() {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        entityManager.getTransaction().commit();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        entityManager.getTransaction().begin();
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        q.setParameter("id", id);
        User user = q.getResultList().stream().findAny().orElse(null);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public void saveUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteUserById(Long id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from User u where u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public User getUserByName(String name) {
        entityManager.getTransaction().begin();
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.name = :name", User.class);
        q.setParameter("name", name);
        User user = q.getResultList().stream().findAny().orElse(null);
        entityManager.getTransaction().commit();
        return user;
    }
}
