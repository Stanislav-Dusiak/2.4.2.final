package ru.alishev.springcourse.service;

import ru.alishev.springcourse.models.Role;

public interface RoleService {
    Role findRoleByString(String s);
    Role findRoleById(Long id);
}
