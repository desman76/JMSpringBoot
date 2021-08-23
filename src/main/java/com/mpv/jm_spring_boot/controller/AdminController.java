package com.mpv.jm_spring_boot.controller;

import com.mpv.jm_spring_boot.entity.User;
import com.mpv.jm_spring_boot.service.RoleService;
import com.mpv.jm_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("newUser", new User());
        return "Users";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "addPage";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bd,
                         @RequestParam(value = "listRoles", required = false) String roles) {
//        if (bd.hasErrors()) return "redirect:/admin";
//        if (admin != null) {
//            user.addRole(roleService.getByName("ROLE_ADMIN"));
//        }
//        user.addRole(roleService.getByName("ROLE_USER"));
        if (roles.contains("admin")) {
            user.addRole(roleService.getByName("ROLE_ADMIN"));
        }
        if (roles.contains("user")) {
            user.addRole(roleService.getByName("ROLE_USER"));
        }
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin",
                user.getRoles().stream().anyMatch(r -> r.getRole().equals("ROLE_ADMIN")));
        return "editPage";
    }

    @PatchMapping
    public String edit(@ModelAttribute("user") @Valid User user,
//                       BindingResult bd,
                       @RequestParam(value = "listRoles", required = false) String roles) {
//        System.out.println("USER DATA: " + user);
//        if (bd.hasErrors()) {
//            model.addAttribute("users", userService.getAll());
//            return "redirect:/admin";
//        }
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
}
