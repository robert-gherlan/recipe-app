package com.gherlan.recipeapp.service;

import com.gherlan.recipeapp.converter.RecipeCommandToRecipe;
import com.gherlan.recipeapp.converter.RecipeToRecipeCommand;
import com.gherlan.recipeapp.model.Recipe;
import com.gherlan.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    void findAll() {
        List<Recipe> recipes = Collections.singletonList(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipes);
        Iterable<Recipe> foundRecipes = recipeService.findAll();
        assertThat(foundRecipes).isEqualTo(recipes);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        // when
        Optional<Recipe> recipeReturned = recipeService.findById(1L);

        // then
        assertThat(recipeReturned).isNotNull().isNotEmpty();
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipesTest() throws Exception {
        // given
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet();
        recipesData.add(recipe);
        when(recipeService.findAll()).thenReturn(recipesData);

        // when
        Iterable<Recipe> foundData = recipeService.findAll();

        // then
        assertThat(foundData).hasSize(1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }
}