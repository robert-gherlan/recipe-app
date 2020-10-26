package com.gherlan.recipeapp.converter;

import com.gherlan.recipeapp.command.UnitOfMeasureCommand;
import com.gherlan.recipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UnitOfMeasureCommandToUnitOfMeasureTest {
    private static final String DESCRIPTION = "description";
    private static final Long LONG_VALUE = 1L;
    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    public void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() {
        assertThat(converter.convert(null)).isNull();
    }

    @Test
    public void testEmptyObject() {
        assertThat(converter.convert(new UnitOfMeasureCommand())).isNotNull();
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertThat(uom).isNotNull();
        assertThat(uom.getId()).isEqualTo(LONG_VALUE);
        assertThat(uom.getDescription()).isEqualTo(DESCRIPTION);
    }
}