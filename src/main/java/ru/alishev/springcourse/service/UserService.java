package ru.alishev.springcourse.service;

import ru.alishev.springcourse.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
    User getUserByName(String name);
}
