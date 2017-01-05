package uk.ac.brighton.uni.ch629.catshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.brighton.uni.ch629.catshop.OrderAddProduct;
import uk.ac.brighton.uni.ch629.catshop.connections.subscription.SubscriptionManager;
import uk.ac.brighton.uni.ch629.catshop.data.Order;
import uk.ac.brighton.uni.ch629.catshop.data.OrderProduct;
import uk.ac.brighton.uni.ch629.catshop.data.Product;
import uk.ac.brighton.uni.ch629.catshop.data.services.interfaces.OrderService;
import uk.ac.brighton.uni.ch629.catshop.update.AddOrder;

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

    @PostMapping(value = "/order")
    public int addOrder(@RequestBody AddOrder addOrder) { //The Cashier needs to tell the customer the OrderID
        Order createdOrder = orderService.create(addOrder.getOrder());
        //TODO: Send AddOrder with the new OrderID to the Warehouse & Maybe just send the OrderID to the Collection
        AddOrder newAddOrder = new AddOrder(createdOrder);
        SubscriptionManager.getInstance().sendUpdate(newAddOrder);
        return createdOrder.getOrderID();
    }

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
