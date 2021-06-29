package ru.alishev.springcourse.dao;

import ru.alishev.springcourse.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
    User getUserByName(String name);
}
