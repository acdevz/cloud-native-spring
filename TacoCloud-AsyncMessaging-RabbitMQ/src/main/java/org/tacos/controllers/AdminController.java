package org.tacos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tacos.services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/deleteOrder")
    public String deleteOrder(Long id) {
        adminService.deleteOrderById(id);
        return "redirect:/";
    }

    @PostMapping("/deleteAllOrders")
    public String deleteAllOrders() {
        adminService.deleteAllOrders();
        return "redirect:/";
    }

}
