package org.tacos.models;

import lombok.Data;

@Data
public class IngredientRef {
    private final Long taco;
    private final String ingredient;
}
