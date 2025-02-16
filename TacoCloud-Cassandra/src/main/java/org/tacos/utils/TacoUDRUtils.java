package org.tacos.utils;

import org.tacos.models.Ingredient;
import org.tacos.models.IngredientUDT;
import org.tacos.models.Taco;
import org.tacos.models.TacoUDT;

import java.util.List;
import java.util.stream.Collectors;

public class TacoUDRUtils {
    public static TacoUDT toTacoUDT(Taco taco){
        return new TacoUDT(taco.getName(), taco.getIngredients());
    }

    public static IngredientUDT toIngredientUDT(Ingredient ingredient){
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

    public static List<IngredientUDT> toIngredientUDTs(List<Ingredient> ingredients){
        return ingredients.stream().map(TacoUDRUtils::toIngredientUDT).collect(Collectors.toList());
    }
}
