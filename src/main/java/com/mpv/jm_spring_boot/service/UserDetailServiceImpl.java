package com.mpv.jm_spring_boot.service;

import com.mpv.jm_spring_boot.dao.BasicDao;
import com.mpv.jm_spring_boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private BasicDao<User> userDao;

    @Autowired
    public void setUserDao(BasicDao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByName(username);
        if (user == null) throw new UsernameNotFoundException(String.format("Пользователь %s не найден", username));
        return user;
    }
}
