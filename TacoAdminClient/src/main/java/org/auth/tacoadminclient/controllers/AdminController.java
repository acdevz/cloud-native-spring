package org.auth.tacoadminclient.controllers;

import jakarta.validation.Valid;
import org.auth.tacoadminclient.models.Ingredient;
import org.auth.tacoadminclient.services.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin/ingredients")
public class AdminController {
    private final IngredientService ingredientService;

    public AdminController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @ModelAttribute(name = "ingredient")
    public Ingredient ingredient(){
        return new Ingredient();
    }

    @GetMapping
    public String getAllIngredients(Model model){
        Iterable<Ingredient> ingredients = ingredientService.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredientsAdmin";
    }

    @PostMapping()
    public String addIngredient(@Valid Ingredient ingredient, Errors errors){
        if(errors.hasErrors())
            return "ingredientsAdmin";
        ingredientService.addIngredient(ingredient);
        return "redirect:/admin/ingredients";
    }

    @PostMapping("/deleteIngredient")
    public String deleteIngredient(@RequestParam String id){
        ingredientService.deleteIngredient(id);
        return "redirect:/admin/ingredients";
    }
}
