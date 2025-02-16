package org.tacos.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.tacos.models.Taco;
import org.tacos.models.TacoOrder;
import org.tacos.models.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    TacoOrder save(TacoOrder order);

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
