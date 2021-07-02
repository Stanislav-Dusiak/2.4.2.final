package ru.alishev.springcourse.controllers;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.alishev.springcourse.dao.UserDaoImpl;
import ru.alishev.springcourse.models.User;

import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserDaoImpl userDaoImpl;

    public UserController(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @RequestMapping(value = "/user{name}")
    public String edit(Model model, @PathVariable("name") String name) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (name.equals(user.getName()) || roles.contains("ROLE_ADMIN")) {
            model.addAttribute("user", userDaoImpl.getUserByName(name));
            return "user";
        } else {
            return "userAccessDenied";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
