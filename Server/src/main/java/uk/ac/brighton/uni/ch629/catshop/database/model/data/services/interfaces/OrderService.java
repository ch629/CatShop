package uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces;

import uk.ac.brighton.uni.ch629.catshop.database.model.Order;
import uk.ac.brighton.uni.ch629.catshop.database.model.OrderProduct;
import uk.ac.brighton.uni.ch629.catshop.database.model.Product;

import java.util.List;

public interface OrderService { //NOTE: Might not need all the addProduct methods, just figure out which one's to keep when implementing this.
    Order create(Order order);

    Order delete(int id);

    List<Order> findAll();

    Order update(Order order);

    Order findByID(int id);

    Order addProduct(Order order, Product product, int quantity);

    Order addProduct(OrderProduct product);

    Order addProduct(int orderID, Product product, int quantity);

    Order addProduct(int orderID, int productID, int quantity);
}
