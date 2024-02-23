package com.ravi.dynamiccontroller.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.dynamiccontroller.service.GenericService;

import java.io.Serializable;
import java.util.List;

public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public GenericServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T getById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(ID id, T entity) {
        T existingEntity = repository.findById(id).orElse(null);
        if (existingEntity != null) {
            BeanUtils.copyProperties(entity, existingEntity);
            return repository.save(existingEntity);
        }
        return null;
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }
}
