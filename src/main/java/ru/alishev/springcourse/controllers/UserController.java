package ru.alishev.springcourse.controllers;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import ru.alishev.springcourse.models.User;
import ru.alishev.springcourse.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user{name}")
    public String edit(Model model, @PathVariable("name") String name) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (name.equals(user.getName()) || roles.contains("ROLE_ADMIN")) {
            model.addAttribute("user", userService.getUserByName(name));
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
