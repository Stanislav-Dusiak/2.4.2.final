package ru.alishev.springcourse.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Set;

@Component
public class UserDaoImpl implements UserDao {

    private EntityManagerFactory factory;

    public UserDaoImpl(@Autowired EntityManagerFactory factory) {
        this.factory = factory;
    }


    @Override
    public List<User> getAllUsers() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        List<User> users = manager.createQuery("select u from User u").getResultList();
        manager.getTransaction().commit();
        manager.close();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        User user = (User) manager.createQuery("select u from User u where u.id=?1").setParameter(1, id).getSingleResult();
        manager.getTransaction().commit();
//        manager.getTransaction().begin();
//        Set<Role> roles = Set.copyOf(manager.createQuery("select roles_id from user_role where id=?1").setParameter(1, id).getResultList());
//        user.setRoles(roles);
//        manager.getTransaction().commit();
        manager.close();
        return user;
    }

    @Override
    public void saveUser(User user) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void updateUser(User user) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteUserById(Long id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        User user = (User) manager.createQuery("select u from User u where u.id=?1").setParameter(1,id).getSingleResult();
        manager.remove(user);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public User getUserByName(String name) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        User user = (User) manager.createQuery("select u from User u where u.username=?1").setParameter(1, name).getSingleResult();
        manager.getTransaction().commit();
        manager.close();
        return user;
    }
}
