package org.tacos.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.tacos.models.TacoOrder;
import org.tacos.repositories.OrderRepository;

@Service
public class AdminService {
    private final OrderRepository orderRepository;

    public AdminService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<TacoOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
