package com.gherlan.recipeapp.converter;

import com.gherlan.recipeapp.command.IngredientCommand;
import com.gherlan.recipeapp.model.Ingredient;
import com.gherlan.recipeapp.model.Recipe;
import com.gherlan.recipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class IngredientToIngredientCommandTest {
    private static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "Cheeseburger";
    private static final Long UOM_ID = 2L;
    private static final Long ID_VALUE = 1L;
    private IngredientToIngredientCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullConvert() {
        assertThat(converter.convert(null)).isNull();
    }

    @Test
    public void testEmptyObject() {
        assertThat(converter.convert(new Ingredient())).isNotNull();
    }

    @Test
    public void testConvertNullUOM() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUnitOfMeasure(null);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertThat(ingredientCommand.getUnitOfMeasure()).isNull();
        assertThat(ingredientCommand.getId()).isEqualTo(ID_VALUE);
        assertThat(ingredientCommand.getAmount()).isEqualTo(AMOUNT);
        assertThat(ingredientCommand.getDescription()).isEqualTo(DESCRIPTION);
    }

    @Test
    public void testConvertWithUom() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);

        ingredient.setUnitOfMeasure(unitOfMeasure);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertThat(ingredientCommand.getId()).isEqualTo(ID_VALUE);
        assertThat(ingredientCommand.getUnitOfMeasure()).isNotNull();
        assertThat(ingredientCommand.getUnitOfMeasure().getId()).isEqualTo(UOM_ID);
        assertThat(ingredientCommand.getAmount()).isEqualTo(AMOUNT);
        assertThat(ingredientCommand.getDescription()).isEqualTo(DESCRIPTION);
    }
}
