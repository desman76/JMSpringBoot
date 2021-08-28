package com.mpv.jm_spring_boot.controller;

import com.mpv.jm_spring_boot.entity.User;
import com.mpv.jm_spring_boot.service.RoleService;
import com.mpv.jm_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private UserService userService;
    private RoleService roleService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        userService.add(user);
        return user;
    }

    @PatchMapping
    public void edit(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") long id) {
        userService.deleteById(id);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") long id) {
        return userService.getById(id);
    }

    private void manageRoles(User user, String roles) {
        if (roles.contains("admin")) {
            user.addRole(roleService.getByName("ROLE_ADMIN"));
        }
        if (roles.contains("user")) {
            user.addRole(roleService.getByName("ROLE_USER"));
        }
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
