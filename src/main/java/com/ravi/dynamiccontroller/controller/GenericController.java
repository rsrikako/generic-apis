package com.ravi.dynamiccontroller.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.dynamiccontroller.repository.GenericRepository;
import com.ravi.dynamiccontroller.service.GenericService;
import com.ravi.dynamiccontroller.specifications.GenericSpecifications;

import java.io.Serializable;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class GenericController<T, V extends Serializable> {

    @Autowired
    public GenericRepository<T, V> repository;

    public GenericService<T, V> service;

    public GenericSpecifications<T> specifications;

    public GenericController(GenericSpecifications<T> specifications, GenericService<T, V> service) {
        this.specifications = specifications;
        this.service = service;
    }

    @GetMapping
    public Page<T> getData(
            @RequestParam Map<String, String> params,
            @RequestParam(name = "sort", required = false) String sort,
            Pageable pageable) {
                System.out.println(pageable);
        Specification<T> spec = specifications.byCriteria(params, sort);
        return repository.findAll(spec, pageable);
    }

        @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable V id) {
        T entity = service.getById(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }
        @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        T createdEntity = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable V id, @RequestBody T entity) {
        T updatedEntity = service.update(id, entity);
        return updatedEntity != null ? ResponseEntity.ok(updatedEntity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable V id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}