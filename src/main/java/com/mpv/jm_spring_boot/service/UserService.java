package com.mpv.jm_spring_boot.service;

import com.mpv.jm_spring_boot.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends BasicService<User>, UserDetailsService {
}
