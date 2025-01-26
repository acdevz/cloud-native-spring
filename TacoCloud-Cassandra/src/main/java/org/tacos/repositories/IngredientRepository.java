package org.tacos.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.tacos.models.Ingredient;

public interface IngredientRepository extends CassandraRepository<Ingredient, String> {}
