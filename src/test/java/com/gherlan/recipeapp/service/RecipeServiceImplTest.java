package com.gherlan.recipeapp.service;

import com.gherlan.recipeapp.model.Recipe;
import com.gherlan.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void findAll() {
        List<Recipe> recipes = Collections.singletonList(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipes);
        Iterable<Recipe> foundRecipes = recipeService.findAll();
        assertThat(foundRecipes).isEqualTo(recipes);
        verify(recipeRepository, times(1)).findAll();
    }
}