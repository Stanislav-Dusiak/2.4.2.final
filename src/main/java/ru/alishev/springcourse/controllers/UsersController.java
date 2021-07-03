package ru.alishev.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.RoleDao;
import ru.alishev.springcourse.dao.UserDao;
import ru.alishev.springcourse.models.Role;
import ru.alishev.springcourse.models.User;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class UsersController {

    private final UserDao userDaoImpl;
    private final RoleDao roleDao;

    @Autowired
    public UsersController(UserDao userDaoImpl, RoleDao roleDao) {
        this.userDaoImpl = userDaoImpl;
        this.roleDao = roleDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("admin", userDaoImpl.getAllUsers());
        return "admin/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userDaoImpl.getUserById(id));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping
    public String addUser(@ModelAttribute("User") User user, @RequestParam(value = "AdminId", defaultValue = "2") String[] userRoleId) {
        setRolesFromStringArr(user, userRoleId);
        userDaoImpl.saveUser(user);
        return "redirect:/admin";
    }

    private void setRolesFromStringArr(User user, String[] roles) {
        String ADMIN_ROLE_ID = "1";
        Set<String> userRoles = Set.of(roles);
        if (userRoles.contains(ADMIN_ROLE_ID)) {
            user.setRoles(Set.of(roleDao.findRoleByString("ROLE_ADMIN"), roleDao.findRoleByString("ROLE_USER")));
        } else {
            user.setRoles(Set.of(roleDao.findRoleByString("ROLE_USER")));
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userDaoImpl.getUserById(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userDaoImpl.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userDaoImpl.deleteUserById(id);
        return "redirect:/admin";
    }
}
