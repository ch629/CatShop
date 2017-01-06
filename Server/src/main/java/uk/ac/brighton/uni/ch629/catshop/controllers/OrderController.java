package uk.ac.brighton.uni.ch629.catshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.brighton.uni.ch629.catshop.connections.subscription.SubscriptionManager;
import uk.ac.brighton.uni.ch629.catshop.data.Basket;
import uk.ac.brighton.uni.ch629.catshop.data.Order;
import uk.ac.brighton.uni.ch629.catshop.data.OrderProduct;
import uk.ac.brighton.uni.ch629.catshop.data.Product;
import uk.ac.brighton.uni.ch629.catshop.data.services.interfaces.OrderService;
import uk.ac.brighton.uni.ch629.catshop.update.AddOrderToShopDisplay;
import uk.ac.brighton.uni.ch629.catshop.update.AddOrderToWarehouse;
import uk.ac.brighton.uni.ch629.catshop.update.CollectOrder;
import uk.ac.brighton.uni.ch629.catshop.update.PickOrder;

import java.util.HashMap;
import java.util.List;

@RestController("order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/order/{id}/collect")
    public void collectOrder(@PathVariable int id) {
        SubscriptionManager.getInstance().sendUpdate(new CollectOrder(id));
    }

    @PostMapping(value = "/order/{id}/pick")
    public void pickOrder(@PathVariable int id) {
        SubscriptionManager.getInstance().sendUpdate(new PickOrder(id));
    }

    @PostMapping(value = "/order/product")
    public void addProductToOrder(@RequestBody OrderProduct orderProduct) {
        orderService.addProduct(orderProduct);
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
    public int addOrder(@RequestBody Basket basket) { //TODO: Could take a Map<Product, Integer> instead
        Order createdOrder = orderService.create(basket.asOrder());

        SubscriptionManager.getInstance().sendUpdate(new AddOrderToWarehouse(createdOrder));
        SubscriptionManager.getInstance().sendUpdate(new AddOrderToShopDisplay(createdOrder.getOrderID()));

        //TODO: Reduce Stock of Products

        return createdOrder.getOrderID();
    }

    /*@PostMapping(value = "/order")
    public int addOrder(@RequestBody AddOrder addOrder) { //For the Cashier to tell the Customer
        Order createdOrder = orderService.create(addOrder.getOrder());
        //TODO: Send AddOrder with the new OrderID to the Warehouse & Maybe just send the OrderID to the Collection
        AddOrder newAddOrder = new AddOrder(createdOrder);
        SubscriptionManager.getInstance().sendUpdate(newAddOrder);
        return createdOrder.getOrderID();
    }*/

    @PostMapping(value = {"/order/basket"})
    public void addBasketToOrder(@RequestBody HashMap<Product, Integer> products) {
        Order order = new Order(products);
        orderService.create(order);
    }
}

//TODO: Error Failed to evaluate Jackson deserialization for type [[simple type, class uk.ac.brighton.uni.ch629.catshop.update.AddOrder]]: com.fasterxml.jackson.databind.JsonMappingException: Could not find creator property with name 'order' (in class uk.ac.brighton.uni.ch629.catshop.data.OrderProduct)