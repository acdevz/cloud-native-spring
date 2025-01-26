package org.tacos.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.tacos.models.Ingredient;
import org.tacos.models.IngredientUDT;
import org.tacos.repositories.IngredientRepository;

import java.util.Optional;

@Component
public class StringToIngredientUDTConverter implements Converter<String, IngredientUDT> {
    private final IngredientRepository ingredientRepository;

    public StringToIngredientUDTConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id){
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        return ingredient.map(value -> new IngredientUDT(value.getName(), value.getType())).orElse(null);
    }
}
