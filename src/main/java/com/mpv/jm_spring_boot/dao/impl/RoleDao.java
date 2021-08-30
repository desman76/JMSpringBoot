package com.mpv.jm_spring_boot.dao.impl;

import com.mpv.jm_spring_boot.dao.BasicDao;
import com.mpv.jm_spring_boot.entity.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDao implements BasicDao<Role> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role add(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Override
    public Role update(Role role) {
        return role;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAll() {
        return entityManager.createQuery("FROM Role").getResultList();
    }

    @Override
    public Role getById(long id) {
        return null;
    }

    @Override
    public Role getByName(String name) {
        return (Role) entityManager.createQuery("FROM Role where role=: name")
                 .setParameter("name", name)
                 .getSingleResult();
    }
}
