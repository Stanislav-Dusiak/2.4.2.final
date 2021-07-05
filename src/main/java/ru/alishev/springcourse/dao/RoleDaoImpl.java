package ru.alishev.springcourse.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import java.util.Optional;

@Component
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager manager;

    @Override
    public Role findRoleByString(String s) {
        Optional<Role> role = Optional.of((Role) manager.createQuery("select r from Role r where r.role=?1").setParameter(1, s).getSingleResult());
        if (role.isPresent()) {
            return role.get();
        }
        throw new IllegalArgumentException("Invalid role");
    }

    @Override
    public Role findRoleById(Long id) {
        Optional<Role> role = Optional.of((Role) manager.createQuery("select r from Role r where r.id=?1").setParameter(1, id).getSingleResult());
        if (role.isPresent()) {
            return role.get();
        }
        throw new IllegalArgumentException("Invalid role");
    }
}
