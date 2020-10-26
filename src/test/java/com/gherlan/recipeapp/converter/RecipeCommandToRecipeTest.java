package com.gherlan.recipeapp.converter;

import com.gherlan.recipeapp.command.CategoryCommand;
import com.gherlan.recipeapp.command.IngredientCommand;
import com.gherlan.recipeapp.command.NotesCommand;
import com.gherlan.recipeapp.command.RecipeCommand;
import com.gherlan.recipeapp.model.Difficulty;
import com.gherlan.recipeapp.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeCommandToRecipeTest {
    private static final Long RECIPE_ID = 1L;
    private static final Integer COOK_TIME = Integer.valueOf("5");
    private static final Integer PREP_TIME = Integer.valueOf("7");
    private static final String DESCRIPTION = "My Recipe";
    private static final String DIRECTIONS = "Directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Integer SERVINGS = Integer.valueOf("3");
    private static final String SOURCE = "Source";
    private static final String URL = "Some URL";
    private static final Long CAT_ID_1 = 1L;
    private static final Long CAT_ID2 = 2L;
    private static final Long INGRED_ID_1 = 3L;
    private static final Long INGRED_ID_2 = 4L;
    private static final Long NOTES_ID = 9L;
    private RecipeCommandToRecipe converter;

    @BeforeEach
    public void setUp() {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(), new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()), new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() {
        assertThat(converter.convert(null)).isNull();
    }

    @Test
    public void testEmptyObject() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setNotes(new NotesCommand());
        assertThat(converter.convert(recipeCommand)).isNotNull();
    }

    @Test
    public void convert() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServing(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);

        recipeCommand.setNotes(notes);

        CategoryCommand category = new CategoryCommand();
        category.setId(CAT_ID_1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID2);

        recipeCommand.getCategories().add(category);
        recipeCommand.getCategories().add(category2);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);

        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);

        //when
        Recipe recipe = converter.convert(recipeCommand);

        //then
        assertThat(recipe).isNotNull();
        assertThat(recipe.getId()).isEqualTo(RECIPE_ID);
        assertThat(recipe.getCookTime()).isEqualTo(COOK_TIME);
        assertThat(recipe.getPrepTime()).isEqualTo(PREP_TIME);
        assertThat(recipe.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(recipe.getDifficulty()).isEqualTo(DIFFICULTY);
        assertThat(recipe.getDirections()).isEqualTo(DIRECTIONS);
        assertThat(recipe.getServing()).isEqualTo(SERVINGS);
        assertThat(recipe.getSource()).isEqualTo(SOURCE);
        assertThat(recipe.getUrl()).isEqualTo(URL);
        assertThat(recipe.getNotes().getId()).isEqualTo(NOTES_ID);
        assertThat(recipe.getCategories()).hasSize(2);
        assertThat(recipe.getIngredients()).hasSize(2);
    }
}
