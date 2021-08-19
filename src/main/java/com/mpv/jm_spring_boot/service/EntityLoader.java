package com.mpv.jm_spring_boot.service;

import com.mpv.jm_spring_boot.entity.Role;
import com.mpv.jm_spring_boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class EntityLoader {

    private BasicService<User> userService;
    private BasicService<Role> roleService;

    @PostConstruct
    public void init() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        User user1 = new User("user_1", "1", "Bob", 20);
        user1.addRole(userRole);

        User user2 = new User("user_2", "2", "Bill", 25);
        user2.addRole(adminRole);

        User user3 = new User("user_3", "3", "Ted", 35);
        user3.getRoles().add(userRole);
        user3.getRoles().add(adminRole);

        roleService.add(userRole);
        roleService.add(adminRole);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        System.out.println("table created");
    }

    @Autowired
    public void setUserService(BasicService<User> userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(BasicService<Role> roleService) {
        this.roleService = roleService;
    }
}
