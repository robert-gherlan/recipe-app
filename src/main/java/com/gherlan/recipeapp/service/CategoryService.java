package com.gherlan.recipeapp.service;

import com.gherlan.recipeapp.model.Category;

import java.util.Optional;

public interface CategoryService extends AbstractService<Category, Long> {
    Optional<Category> findByDescription(String description);
}
