package com.gherlan.recipeapp.service;

import com.gherlan.recipeapp.command.RecipeCommand;
import com.gherlan.recipeapp.converter.RecipeCommandToRecipe;
import com.gherlan.recipeapp.converter.RecipeToRecipeCommand;
import com.gherlan.recipeapp.model.Recipe;
import com.gherlan.recipeapp.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeServiceImpl extends AbstractServiceImpl<Recipe, Long> implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        super(recipeRepository);
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Optional<Recipe> findByDescription(String description) {
        return recipeRepository.findByDescription(description);
    }

    @Override
    public RecipeCommand save(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
