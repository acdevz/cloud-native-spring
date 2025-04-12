package org.tacokitchen.dao;

import lombok.Data;

import java.io.Serializable;

@Data
public class Ingredient implements Serializable {
    private String name;
    private Type type;
    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
    public Ingredient() {}
}