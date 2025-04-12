package org.tacos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tacos.messaging.KitchenUI;

@Controller
public class KitchenController {
    private final KitchenUI ui;

    public KitchenController(KitchenUI ui) {
        this.ui = ui;
    }

    @GetMapping("/kitchen")
    public String processOrder(Model model) {
        model.addAttribute("pendingOrdersCount", ui.getPendingOrdersCount());
        model.addAttribute("order", ui.getOrder());
        return "kitchen";
    }
}
