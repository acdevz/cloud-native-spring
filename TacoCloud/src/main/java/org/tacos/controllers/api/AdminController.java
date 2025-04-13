package org.tacos.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tacos.models.TacoOrder;
import org.tacos.services.AdminService;

@RestController
@RequestMapping("/admin/api")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/allOrders")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<TacoOrder> getAllOrders() {
        return adminService.getAllOrders();
    }

    @DeleteMapping("/deleteOrder/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        adminService.deleteOrderById(id);
    }

    @DeleteMapping("/deleteAllOrders")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllOrders() {
        adminService.deleteAllOrders();
    }

}
