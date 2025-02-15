package org.auth.tacoadminclient.models;

import lombok.Data;

@Data
public class Ingredient {
    private String id;
    private String name;
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    public static Ingredient of(String id, String name, Type type) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName(name);
        ingredient.setType(type);
        return ingredient;
    }
}
