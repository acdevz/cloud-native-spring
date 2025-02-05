package org.tacos.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tacos.models.Ingredient;
import org.tacos.repositories.IngredientRepository;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
public class IngredientsController {
    private final IngredientRepository ingredientRepository;

    public IngredientsController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping()
    public Iterable<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(
            @PathVariable String id
    ){
        return ingredientRepository.getIngredientById(id)
                .map(ingredient -> new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Ingredient>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient createIngredient(
            @RequestBody Ingredient ingredient
    ){
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(
            @PathVariable String id
    ){
        ingredientRepository.deleteById(id);
    }
}
