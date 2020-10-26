package com.gherlan.recipeapp.converter;

import com.gherlan.recipeapp.command.RecipeCommand;
import com.gherlan.recipeapp.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeToRecipeCommandTest {
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
    private RecipeToRecipeCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() {
        assertThat(converter.convert(null)).isNull();
    }

    @Test
    public void testEmptyObject() {
        assertThat(converter.convert(new Recipe())).isNotNull();
    }

    @Test
    public void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServing(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        Category category = new Category();
        category.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID2);

        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGRED_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertThat(command).isNotNull();
        assertThat(command.getId()).isEqualTo(RECIPE_ID);
        assertThat(command.getCookTime()).isEqualTo(COOK_TIME);
        assertThat(command.getPrepTime()).isEqualTo(PREP_TIME);
        assertThat(command.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(command.getDifficulty()).isEqualTo(DIFFICULTY);
        assertThat(command.getDirections()).isEqualTo(DIRECTIONS);
        assertThat(command.getServing()).isEqualTo(SERVINGS);
        assertThat(command.getSource()).isEqualTo(SOURCE);
        assertThat(command.getUrl()).isEqualTo(URL);
        assertThat(command.getNotes().getId()).isEqualTo(NOTES_ID);
        assertThat(command.getCategories()).hasSize(2);
        assertThat(command.getIngredients()).hasSize(2);
    }
}
