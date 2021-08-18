package com.mpv.jm_spring_boot.service;

import com.mpv.jm_spring_boot.dao.BasicDao;
import com.mpv.jm_spring_boot.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService implements BasicService<Role>{

    @Autowired
    private BasicDao<Role> roleDao;

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void deleteById(long id) {
        roleDao.deleteById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public Role getById(long id) {
        return roleDao.getById(id);
    }

    @Override
    public Role getByName(String name) {
        return roleDao.getByName(name);
    }
}
