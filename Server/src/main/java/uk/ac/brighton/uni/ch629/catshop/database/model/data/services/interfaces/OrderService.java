package uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces;

import uk.ac.brighton.uni.ch629.catshop.database.model.Order;

import java.util.List;

public interface OrderService {
    Order create(Order order);

    Order delete(int id);

    List<Order> findAll();

    Order update(Order order);

    Order findByID(int id);
}
