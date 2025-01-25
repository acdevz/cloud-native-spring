package org.tacos.models;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class IngredientRef {
    private String ingredient;

    public IngredientRef(String ingredient) {
        this.ingredient = ingredient;
    }
}
