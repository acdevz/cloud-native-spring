package org.auth.tacoadminclient.services;

import org.auth.tacoadminclient.models.Ingredient;

public interface IngredientService {
    Iterable<Ingredient> findAll();
    Ingredient addIngredient(Ingredient ingredient);
}
