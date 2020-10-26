package com.gherlan.recipeapp.converter;

import com.gherlan.recipeapp.command.IngredientCommand;
import com.gherlan.recipeapp.command.UnitOfMeasureCommand;
import com.gherlan.recipeapp.model.Ingredient;
import com.gherlan.recipeapp.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class IngredientCommandToIngredientTest {
    private static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "Cheeseburger";
    private static final long ID_VALUE = 1L;
    private static final long UOM_ID = 2L;
    private IngredientCommandToIngredient converter;

    @BeforeEach
    public void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() {
        assertThat(converter.convert(null)).isNull();
    }

    @Test
    public void testEmptyObject() {
        assertThat(converter.convert(new IngredientCommand())).isNotNull();
    }

    @Test
    public void convert() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUnitOfMeasure(unitOfMeasureCommand);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertThat(ingredient).isNotNull();
        assertThat(ingredient.getUnitOfMeasure()).isNotNull();
        assertThat(ingredient.getId()).isEqualTo(ID_VALUE);
        assertThat(ingredient.getAmount()).isEqualTo(AMOUNT);
        assertThat(ingredient.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(ingredient.getUnitOfMeasure().getId()).isEqualTo(UOM_ID);
    }

    @Test
    public void convertWithNullUOM() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertThat(ingredient).isNotNull();
        assertThat(ingredient.getUnitOfMeasure()).isNull();
        assertThat(ingredient.getId()).isEqualTo(ID_VALUE);
        assertThat(ingredient.getAmount()).isEqualTo(AMOUNT);
        assertThat(ingredient.getDescription()).isEqualTo(DESCRIPTION);
    }
}
