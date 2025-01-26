package org.tacos.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.tacos.models.TacoOrder;

import java.util.UUID;

public interface OrderRepository extends CassandraRepository<TacoOrder, UUID> {}
