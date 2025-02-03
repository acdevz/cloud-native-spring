package org.tacos.controllers;

import jakarta.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.tacos.models.TacoOrder;
import org.tacos.models.User;
import org.tacos.props.OrderProps;
import org.tacos.repositories.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderProps props;
    private final OrderProps orderProps;

    public OrderController(OrderRepository orderRepository, OrderProps props, OrderProps orderProps) {
        this.orderRepository = orderRepository;
        this.props = props;
        this.orderProps = orderProps;
    }

    @PostMapping
    public String processOrder(
            @Valid TacoOrder order,
            Errors errors,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user
    ){
        if(errors.hasErrors()){
            return "order";
        }
        order.setUser(user);
        orderRepository.save(order);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping("/current")
    public String orderForm(
            TacoOrder tacoOrder,
            Model model,
            @AuthenticationPrincipal User user
    ){
        tacoOrder.setDeliveryName(user.getFullName());
        tacoOrder.setDeliveryZip((user.getZip()));
        model.addAttribute("user", user);
        return "order";
    }

    @GetMapping("/history")
    public String orderHistoryOfUsers(
            @AuthenticationPrincipal User user,
            Model model,
            @RequestParam(required = false) Integer p
    ){
        Pageable pageable = PageRequest.of(p != null ? p : 0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "ordersList";
    }
}
