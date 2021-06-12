package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private static int USER_COUNT;
    private List<User> users;

    {
        users = new ArrayList<>();

        users.add(new User(++USER_COUNT, "Tom"));
        users.add(new User(++USER_COUNT, "Jerry"));
        users.add(new User(++USER_COUNT, "Butch"));
        users.add(new User(++USER_COUNT, "Spike"));
    }

    public List<User> index() {
        return users;
    }

    public User show(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
    }

    public void update(int id, User updateUser) {
        User userToUpdated = show(id);
        userToUpdated.setName(updateUser.getName());
    }

    public void delete(int id) {
        users.removeIf(p -> p.getId() == id);
    }
}
