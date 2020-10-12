package com.gherlan.recipeapp.controller;

import com.gherlan.recipeapp.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping({"", "/"})
    public String getRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipes";
    }
}
