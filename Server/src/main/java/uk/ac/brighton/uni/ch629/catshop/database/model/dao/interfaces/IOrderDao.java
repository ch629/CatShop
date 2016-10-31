package uk.ac.brighton.uni.ch629.catshop.database.model.dao.interfaces;

import uk.ac.brighton.uni.ch629.catshop.database.model.Order;

import java.util.List;

public interface IOrderDao {
    List<Order> getOrders();

    Order getOrder(int orderNumber);

    void updateOrder(Order order);

    void deleteOrder(int id);

    void deleteOrder(Order order);
}