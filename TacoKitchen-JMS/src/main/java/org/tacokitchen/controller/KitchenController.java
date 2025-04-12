package org.tacokitchen.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tacokitchen.dao.TacoOrder;
import org.tacokitchen.messaging.KitchenUI;
import org.tacokitchen.messaging.OrderReceiverService;

@Controller
public class KitchenController {
    private final OrderReceiverService orderReceiverService;
    private final KitchenUI ui;

    public KitchenController(@Qualifier("rabbit") OrderReceiverService orderReceiverService, KitchenUI ui) {
        this.orderReceiverService = orderReceiverService;
        this.ui = ui;
    }

    @GetMapping("/kitchen")
    public String processOrder(Model model) {
        model.addAttribute("pendingOrdersCount", ui.getPendingOrdersCount());
        model.addAttribute("order", ui.getOrder());
        return "kitchen";
    }

    @GetMapping("/kitchen/orders")
    public String showOrders(Model model) {
        TacoOrder order = orderReceiverService.receiveOrder();
        if (order != null) {
            model.addAttribute("order", order);
            return "orders";
        } else {
            return "noOrders";
        }
    }
}
