package com.mpv.jm_spring_boot.controller;

import com.mpv.jm_spring_boot.entity.User;
import com.mpv.jm_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private UserService userService;

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
    public User edit(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        userService.deleteById(id);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") long id) {
        return userService.getById(id);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
