package com.gherlan.recipeapp.service;

import com.gherlan.recipeapp.model.Category;
import com.gherlan.recipeapp.model.Ingredient;

import java.util.Optional;

public interface IngredientService extends AbstractService<Ingredient, Long> {
    Optional<Ingredient> findByDescription(String description);
}
