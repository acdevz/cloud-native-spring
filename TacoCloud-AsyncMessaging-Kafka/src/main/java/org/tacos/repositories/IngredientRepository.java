package org.tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.tacos.models.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);

    Optional<Ingredient> getIngredientById(String id);
}
