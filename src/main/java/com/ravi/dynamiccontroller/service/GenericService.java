package com.ravi.dynamiccontroller.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, ID extends Serializable> {
    T getById(ID id);
    List<T> getAll();
    T save(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}