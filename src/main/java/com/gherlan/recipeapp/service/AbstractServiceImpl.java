package com.gherlan.recipeapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractServiceImpl<T, ID> implements AbstractService<T, ID> {

    private final CrudRepository<T, ID> crudRepository;

    @Transactional
    @Override
    public Optional<T> findById(ID id) {
        return crudRepository.findById(id);
    }

    @Transactional
    @Override
    public Iterable<T> findAll() {
        return crudRepository.findAll();
    }

    @Transactional
    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        return crudRepository.saveAll(entities);
    }

    @Transactional
    @Override
    public void deleteById(ID id) {
        crudRepository.deleteById(id);
    }
}
