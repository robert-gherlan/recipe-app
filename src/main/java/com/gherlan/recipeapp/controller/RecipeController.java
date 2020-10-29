package com.gherlan.recipeapp.controller;

import com.gherlan.recipeapp.command.RecipeCommand;
import com.gherlan.recipeapp.exception.NotFoundException;
import com.gherlan.recipeapp.model.Recipe;
import com.gherlan.recipeapp.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/add-recipe")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "add-recipe-form";
    }

    @PostMapping("/add-recipe")
    public String saveOrUpdate(@Valid @ModelAttribute RecipeCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-recipe-form";
        }
        RecipeCommand savedCommand = recipeService.save(command);
        return "redirect:/recipes/" + savedCommand.getId();
    }

    @GetMapping({"/{id}"})
    public String getSingleRecipe(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("recipe", recipe);
        return "recipe";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "add-recipe-form";
    }

    @PostMapping({"/{id}/delete"})
    public String deleteRecipeById(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/recipes/";
    }
}
