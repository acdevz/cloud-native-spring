package org.tacos.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder {
    @NotBlank(message = "Name is required")
    private String deliveryName;

    @NotBlank(message = "Delivery Zip Code is required")
    private String deliveryZip;

    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-z]$", message = "Valid UPI ID is required")
    private String upiId;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
