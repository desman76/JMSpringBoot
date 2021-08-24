package com.mpv.jm_spring_boot.dao.impl;

import com.mpv.jm_spring_boot.dao.BasicDao;
import com.mpv.jm_spring_boot.entity.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao implements BasicDao<User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(long id) {
        System.out.println("id: " + id);
        entityManager.createQuery("DELETE FROM User where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByName(String email) {
        return (User) entityManager.createQuery("FROM User as u left join fetch u.roles WHERE u.email=:email ")
                .setParameter("email", email).getSingleResult();
    }
}
