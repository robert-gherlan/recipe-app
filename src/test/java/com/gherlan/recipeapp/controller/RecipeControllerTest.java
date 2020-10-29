package com.gherlan.recipeapp.controller;

import com.gherlan.recipeapp.command.RecipeCommand;
import com.gherlan.recipeapp.exception.NotFoundException;
import com.gherlan.recipeapp.handler.GlobalExceptionHandler;
import com.gherlan.recipeapp.model.Recipe;
import com.gherlan.recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.recipeController = new RecipeController(recipeService);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(recipeController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
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
    void getSingleRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.findById(1L)).thenReturn(Optional.of(recipe));
        Model model = new ConcurrentModel();
        assertThat(recipeController.getSingleRecipe(1L, model)).isEqualTo("recipe");
        verify(recipeService, times(1)).findById(1L);
        assertThat(model.getAttribute("recipe")).isEqualTo(recipe);
    }

    @Test
    void getNotExistingRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.findById(1L)).thenThrow(new NotFoundException());
        assertThatThrownBy(() -> recipeController.getSingleRecipe(1L, mock(Model.class))).isInstanceOf(NotFoundException.class);
        verify(recipeService, times(1)).findById(1L);
    }

    @Test
    void getNotExistingRecipeMock() throws Exception {
        when(recipeService.findById(1L)).thenThrow(new NotFoundException());
        mockMvc.perform(get("/recipes/1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404"))
                .andExpect(model().attributeExists("exception"));
        verify(recipeService, times(1)).findById(1L);
    }

    @Test
    void getInvalidId() throws Exception {
        mockMvc.perform(get("/recipes/invalidId"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400"))
                .andExpect(model().attributeExists("exception"));
    }

    @Test
    void testMockMVC() throws Exception {
        mockMvc.perform(get("/recipes/"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes"));
    }

    @Test
    public void testGetAddRecipe() throws Exception {
        mockMvc.perform(get("/recipes/add-recipe"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-recipe-form"))
                .andExpect(model().attributeExists("recipe"));
        verifyNoInteractions(recipeService);
    }

    @Test
    public void testPostNewRecipeValidParameters() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);
        when(recipeService.save(any())).thenReturn(command);
        mockMvc.perform(post("/recipes/add-recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "description")
                .param("prepTime", "10")
                .param("cookTime", "10")
                .param("serving", "5")
                .param("url", "http://recipe.com")
                .param("directions", "recipe directions"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/2"));
        verify(recipeService).save(any());
    }

    @Test
    public void testPostNewRecipeInvalidParameters() throws Exception {
        mockMvc.perform(post("/recipes/add-recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "description"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-recipe-form"));
        verifyNoInteractions(recipeService);
    }

    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipes/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-recipe-form"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(post("/recipes/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/"));

        verify(recipeService, times(1)).deleteById(1L);
    }
}