package org.tacos.repositories;

import org.tacos.models.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
