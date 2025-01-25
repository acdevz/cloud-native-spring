package org.tacos.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table
public class TacoOrder implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    @NotBlank(message = "Name is required")
    private String deliveryName;

    @NotBlank(message = "Delivery Zip Code is required")
    private String deliveryZip;

    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-z]{1,9}$", message = "Valid UPI ID is required")
    private String upiId;

    private Date placedAt = new Date();

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
