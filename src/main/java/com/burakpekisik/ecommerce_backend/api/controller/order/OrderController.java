package com.burakpekisik.ecommerce_backend.api.controller.order;

import com.burakpekisik.ecommerce_backend.model.LocalUser;
import com.burakpekisik.ecommerce_backend.model.WebOrder;
import com.burakpekisik.ecommerce_backend.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<WebOrder> getOrders(@AuthenticationPrincipal LocalUser user) {
        return orderService.getOrders(user);
    }
}
