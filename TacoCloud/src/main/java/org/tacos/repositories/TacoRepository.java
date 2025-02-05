package org.tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.tacos.models.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long>, PagingAndSortingRepository<Taco, Long> {}
