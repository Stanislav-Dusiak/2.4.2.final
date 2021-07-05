package ru.alishev.springcourse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.dao.RoleDao;
import ru.alishev.springcourse.models.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public Role findRoleByString(String s) {
        return roleDao.findRoleByString(s);
    }

    @Override
    public Role findRoleById(Long id)  {
        return roleDao.findRoleById(id);
    }
}
