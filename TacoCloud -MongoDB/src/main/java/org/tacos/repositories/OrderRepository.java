package org.tacos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.tacos.models.TacoOrder;

public interface OrderRepository extends MongoRepository<TacoOrder, String> {
    TacoOrder save(TacoOrder order);
}
