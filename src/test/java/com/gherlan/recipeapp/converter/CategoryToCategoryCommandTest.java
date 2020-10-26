package com.gherlan.recipeapp.converter;

import com.gherlan.recipeapp.command.CategoryCommand;
import com.gherlan.recipeapp.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryToCategoryCommandTest {
    private static final long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    private CategoryToCategoryCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertThat(converter.convert(null)).isNull();
    }

    @Test
    public void testEmptyObject() {
        assertThat(converter.convert(new Category())).isNotNull();
    }

    @Test
    public void convert() {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertThat(categoryCommand.getId()).isEqualTo(ID_VALUE);
        assertThat(categoryCommand.getDescription()).isEqualTo(DESCRIPTION);
    }
}
