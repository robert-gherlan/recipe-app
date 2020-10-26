package com.gherlan.recipeapp.converter;

import com.gherlan.recipeapp.command.UnitOfMeasureCommand;
import com.gherlan.recipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UnitOfMeasureToUnitOfMeasureCommandTest {
    private static final String DESCRIPTION = "description";
    private static final Long LONG_VALUE = 1L;
    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObjectConvert() {
        assertThat(converter.convert(null)).isNull();
    }

    @Test
    public void testEmptyObj() {
        assertThat(converter.convert(new UnitOfMeasure())).isNotNull();
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(LONG_VALUE);
        unitOfMeasure.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        //then
        assertThat(unitOfMeasureCommand.getId()).isEqualTo(LONG_VALUE);
        assertThat(unitOfMeasureCommand.getDescription()).isEqualTo(DESCRIPTION);
    }
}