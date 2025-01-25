package org.tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.tacos.models.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    TacoOrder save(TacoOrder order);
}
