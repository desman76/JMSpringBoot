package com.mpv.jm_spring_boot.controller;

import com.mpv.jm_spring_boot.entity.User;
import com.mpv.jm_spring_boot.service.RoleService;
import com.mpv.jm_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("newUser", new User());
        return "users";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         @RequestParam(value = "listRoles", required = false) String roles) {
        if (roles.contains("admin")) {
            user.addRole(roleService.getByName("ROLE_ADMIN"));
        }
        if (roles.contains("user")) {
            user.addRole(roleService.getByName("ROLE_USER"));
        }
        userService.add(user);
        return "redirect:/admin";
    }

    @PatchMapping
    public String edit(@ModelAttribute("user") @Valid User user,
                       @RequestParam(value = "listRoles", required = false) String roles) {
        if (roles.contains("admin")) {
            user.addRole(roleService.getByName("ROLE_ADMIN"));
        }
        if (roles.contains("user")) {
            user.addRole(roleService.getByName("ROLE_USER"));
        }
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping
    public String delete(@RequestParam("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
