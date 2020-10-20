package com.gherlan.recipeapp.controller;

import com.gherlan.recipeapp.model.Recipe;
import com.gherlan.recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RecipeControllerTest {

    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.recipeController = new RecipeController(recipeService);
    }

    @Test
    void getRecipes() {
        List<Recipe> recipes = Collections.singletonList(new Recipe());
        when(recipeService.findAll()).thenReturn(recipes);
        Model model = new ConcurrentModel();
        assertThat(recipeController.getRecipes(model)).isEqualTo("recipes");
        verify(recipeService, times(1)).findAll();
        assertThat(model.getAttribute("recipes")).isEqualTo(recipes);
    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("recipes"));
    }
}