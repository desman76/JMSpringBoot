package com.mpv.jm_spring_boot.dao;

import java.util.List;

public interface BasicDao<T> {
    T add(T t);
    T update(T t);
    void deleteById(long id);
    List<T> getAll();
    T getById(long id);
    T getByName(String name);
}
