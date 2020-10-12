package com.gherlan.recipeapp.service;

import java.util.Optional;

public interface AbstractService<T, ID> {
    Optional<T> findById(ID id);

    Iterable<T> findAll();

    Iterable<T> saveAll(Iterable<T> categories);
}
