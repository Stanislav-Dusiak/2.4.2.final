package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Role;

@Component
public interface RoleDao {
    Role findRoleByString(String s);
    Role findRoleById(Long id);
}


