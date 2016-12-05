package uk.ac.brighton.uni.ch629.catshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.brighton.uni.ch629.catshop.OrderAddProduct;
import uk.ac.brighton.uni.ch629.catshop.data.Order;
import uk.ac.brighton.uni.ch629.catshop.data.OrderProduct;
import uk.ac.brighton.uni.ch629.catshop.data.Product;
import uk.ac.brighton.uni.ch629.catshop.data.services.interfaces.OrderService;

import java.util.HashMap;
import java.util.List;

@RestController("order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/order/{id}")
    public Order getOrder(@PathVariable int id) {
        return orderService.findByID(id);
    }

    @GetMapping(value = {"/order/all", "/order", "/orders"})
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping(value = {"/order/{id}/hash"})
    public HashMap<Product, Integer> getHashOrders(@PathVariable int id) {
        HashMap<Product, Integer> hashMap = new HashMap<>();
        Order order = orderService.findByID(id);
        order.getProducts().forEach(pair -> hashMap.put(pair.getProduct(), pair.getQuantity()));
        return hashMap;
    }

//    @PostMapping(value = {"/order"})
//    public void addOrder(@RequestBody Order order) { //TODO: Return the OrderID, and accept an Order without the ID
//        Order createdOrder = orderService.create(order);
//        return createdOrder.getOrderID();
//    }

    @PostMapping(value = {"/order"})
    public void addBasketToOrder(@RequestBody HashMap<Product, Integer> products) {
        Order order = new Order(products);
        orderService.create(order);
    }

    @PostMapping(value = "/order/product")
    public void addProductToOrder(@RequestBody OrderProduct orderProduct) {
        orderService.addProduct(orderProduct);
    }

    public void addProductToOrder(@RequestBody OrderAddProduct orderAddProduct) { //NOTE: This will probably never be called, because the Basket is sent.

    }
}
