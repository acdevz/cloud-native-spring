package org.tacos.models;

import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TacoOrder implements Serializable {
    public TacoOrder() {}
    private String deliveryName;
    private String deliveryZip;
    private String upiId;
    private Date placedAt = new Date();
    private List<Taco> tacos = new ArrayList<>();
}
