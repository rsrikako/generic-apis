package com.ravi.dynamiccontroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T, V> extends JpaRepository<T, V>, JpaSpecificationExecutor<T> {
}