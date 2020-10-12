package com.gherlan.recipeapp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        this.category = new Category();
    }

    @Test
    void getId() {
        long idValue = 4L;
        category.setId(idValue);
        assertThat(category.getId()).isEqualTo(idValue);
    }

    @Test
    void getDescription() {
        String description = "description";
        category.setDescription(description);
        assertThat(category.getDescription()).isEqualTo(description);
    }

    @Test
    void getRecipes() {
        Set<Recipe> recipes = Set.of(new Recipe());
        category.setRecipes(recipes);
        assertThat(category.getRecipes()).isEqualTo(recipes);
    }
}