package com.gherlan.recipeapp.service;

import com.gherlan.recipeapp.command.RecipeCommand;
import com.gherlan.recipeapp.converter.RecipeCommandToRecipe;
import com.gherlan.recipeapp.converter.RecipeToRecipeCommand;
import com.gherlan.recipeapp.model.Recipe;
import com.gherlan.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RecipeServiceIT {
    private static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.save(testRecipeCommand);

        //then
        assertThat(savedRecipeCommand.getDescription()).isEqualTo(NEW_DESCRIPTION);
        assertThat(savedRecipeCommand.getId()).isEqualTo(testRecipe.getId());
        assertThat(savedRecipeCommand.getCategories().size()).isEqualTo(testRecipe.getCategories().size());
        assertThat(savedRecipeCommand.getIngredients().size()).isEqualTo(testRecipe.getIngredients().size());
    }
}
