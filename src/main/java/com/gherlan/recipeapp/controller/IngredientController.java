package com.gherlan.recipeapp.controller;

import com.gherlan.recipeapp.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/recipe/{id}/ingredients")
    public String getIngredients(@PathVariable Long id) {
        return "ingredients";
    }
}
