package com.gherlan.recipeapp.service;

import com.gherlan.recipeapp.model.Category;
import com.gherlan.recipeapp.model.Ingredient;
import com.gherlan.recipeapp.repository.CategoryRepository;
import com.gherlan.recipeapp.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl extends AbstractServiceImpl<Ingredient, Long> implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        super(ingredientRepository);
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Optional<Ingredient> findByDescription(String description) {
        return ingredientRepository.findByDescription(description);
    }
}
