package org.auth.tacoadminclient.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Ingredient {
    @NotBlank(message = "Ingredient Id is required.")
    private String id;
    @NotBlank(message = "Ingredient's Name is required.")
    private String name;
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
