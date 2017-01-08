package uk.ac.brighton.uni.ch629.catshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.connections.subscription.SubscriptionManager;
import uk.ac.brighton.uni.ch629.catshop.data.Basket;
import uk.ac.brighton.uni.ch629.catshop.data.Order;
import uk.ac.brighton.uni.ch629.catshop.data.OrderProduct;
import uk.ac.brighton.uni.ch629.catshop.data.Product;
import uk.ac.brighton.uni.ch629.catshop.data.services.interfaces.OrderService;
import uk.ac.brighton.uni.ch629.catshop.update.AddOrder;
import uk.ac.brighton.uni.ch629.catshop.update.AddOrderNew;
import uk.ac.brighton.uni.ch629.catshop.update.PickOrder;
import uk.ac.brighton.uni.ch629.catshop.update.ShopDisplayUpdate;

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
        SubscriptionManager.getInstance().sendUpdate(new ShopDisplayUpdate(id, ShopDisplayUpdate.UpdateReason.COLLECT));
    }

    @PostMapping(value = "/order/{id}/pick")
    public void pickOrder(@PathVariable int id) {
        SubscriptionManager.getInstance().sendUpdate(new PickOrder(id));
        SubscriptionManager.getInstance().sendUpdate(new ShopDisplayUpdate(id, ShopDisplayUpdate.UpdateReason.PICK));
    }

    @PostMapping(value = "/order/product")
    public void addProductToOrder(@RequestBody OrderProduct orderProduct) { //TODO: Remove this?
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
    public HashMap<Product, Integer> getHashOrders(@PathVariable int id) { //TODO: Remove this?
        HashMap<Product, Integer> hashMap = new HashMap<>();
        Order order = orderService.findByID(id);
        order.getProducts().forEach(pair -> hashMap.put(pair.getProduct(), pair.getQuantity()));
        return hashMap;
    }

    @PostMapping(value = "/order")
    public int addOrder(@RequestBody String basketJson) {
        Basket basket = JsonHelper.jsonToObject(basketJson, Basket.class);
        if (basket != null) {
            Order createdOrder = orderService.create(basket.asOrder());

            SubscriptionManager.getInstance().sendUpdate(new AddOrder(createdOrder.asBasket()));
            SubscriptionManager.getInstance().sendUpdate(new AddOrderNew(createdOrder));
            SubscriptionManager.getInstance().sendUpdate(new ShopDisplayUpdate(createdOrder.getOrderID(), ShopDisplayUpdate.UpdateReason.ADD));

            //TODO: Reduce Stock of Products

            return createdOrder.getOrderID();
        }
        return -1;
    }

    @PostMapping(value = {"/order/basket"})
    public void addBasketToOrder(@RequestBody HashMap<Product, Integer> products) { //TODO: Remove this?
        Order order = new Order(products);
        orderService.create(order);
    }
}