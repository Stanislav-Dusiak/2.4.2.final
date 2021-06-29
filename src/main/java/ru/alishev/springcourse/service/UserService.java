package ru.alishev.springcourse.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.dao.RoleDao;
import ru.alishev.springcourse.dao.UserDao;
import ru.alishev.springcourse.models.Role;
import ru.alishev.springcourse.models.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final PasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDao userDao, RoleDao roleDao, PasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.saveUser(user);
    }


    public User findByUsername(String username) {
        return userDao.getUserByName(username);
    }
}