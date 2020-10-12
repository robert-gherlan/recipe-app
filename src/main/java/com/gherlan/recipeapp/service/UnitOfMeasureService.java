package com.gherlan.recipeapp.service;

import com.gherlan.recipeapp.model.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureService extends AbstractService<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
