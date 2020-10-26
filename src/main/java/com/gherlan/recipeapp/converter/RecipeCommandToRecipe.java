package com.gherlan.recipeapp.converter;

import com.gherlan.recipeapp.command.CategoryCommand;
import com.gherlan.recipeapp.command.IngredientCommand;
import com.gherlan.recipeapp.command.RecipeCommand;
import com.gherlan.recipeapp.model.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;


    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setServing(recipeCommand.getServing());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

        Set<CategoryCommand> categories = recipeCommand.getCategories();
        if (categories != null && !categories.isEmpty()) {
            categories.forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        Set<IngredientCommand> ingredients = recipeCommand.getIngredients();
        if (ingredients != null && !ingredients.isEmpty()) {
            ingredients.forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
