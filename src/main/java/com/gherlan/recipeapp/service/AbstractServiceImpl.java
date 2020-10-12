package com.gherlan.recipeapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractServiceImpl<T, ID> implements AbstractService<T, ID> {

    private final CrudRepository<T, ID> crudRepository;

    @Override
    public Optional<T> findById(ID id) {
        return crudRepository.findById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        return crudRepository.saveAll(entities);
    }
}
