package com.gherlan.recipeapp.converter;

import com.gherlan.recipeapp.command.RecipeCommand;
import com.gherlan.recipeapp.model.Category;
import com.gherlan.recipeapp.model.Ingredient;
import com.gherlan.recipeapp.model.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;


    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        RecipeCommand command = new RecipeCommand();
        command.setId(recipe.getId());
        command.setCookTime(recipe.getCookTime());
        command.setPrepTime(recipe.getPrepTime());
        command.setDescription(recipe.getDescription());
        command.setDifficulty(recipe.getDifficulty());
        command.setDirections(recipe.getDirections());
        command.setServing(recipe.getServing());
        command.setSource(recipe.getSource());
        command.setUrl(recipe.getUrl());
        command.setNotes(notesConverter.convert(recipe.getNotes()));

        Set<Category> categories = recipe.getCategories();
        if (categories != null && !categories.isEmpty()) {
            categories.forEach(category -> command.getCategories().add(categoryConverter.convert(category)));
        }

        Set<Ingredient> ingredients = recipe.getIngredients();
        if (ingredients != null && !ingredients.isEmpty()) {
            ingredients.forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }
}
