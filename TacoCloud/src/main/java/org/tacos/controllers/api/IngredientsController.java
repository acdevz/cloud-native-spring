package org.tacos.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tacos.models.Ingredient;
import org.tacos.models.Taco;
import org.tacos.repositories.IngredientRepository;
import org.tacos.repositories.TacoRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin("http://localhost:9090")
public class IngredientsController {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    public IngredientsController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping()
    public Iterable<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(
            @PathVariable String id
    ) {
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
        Ingredient ingredient = ingredientRepository.findById(id).orElseGet(() -> null);
        tacoRepository.findAll().forEach(taco -> {
            taco.getIngredients().remove(ingredient);
            tacoRepository.save(taco);
        });
        ingredientRepository.deleteById(id);
    }
}
