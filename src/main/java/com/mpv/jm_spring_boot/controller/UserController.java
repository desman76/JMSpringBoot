package com.mpv.jm_spring_boot.controller;

import com.mpv.jm_spring_boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;

    @GetMapping
    public String getUserInfo(Principal principal,
                              Authentication authentication,
                              Model model) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "userInfoPage";
    }
}
