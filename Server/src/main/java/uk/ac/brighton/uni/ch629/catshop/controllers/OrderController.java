package uk.ac.brighton.uni.ch629.catshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.brighton.uni.ch629.catshop.database.model.Order;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces.OrderService;

import java.util.List;

@RestController("order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/order/all")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }
}
