package uk.ac.brighton.uni.ch629.catshop.database.model.dao;

import org.hibernate.Session;
import uk.ac.brighton.uni.ch629.catshop.database.HibernateUtil;
import uk.ac.brighton.uni.ch629.catshop.database.model.Order;
import uk.ac.brighton.uni.ch629.catshop.database.model.dao.interfaces.IOrderDao;

import java.util.List;

public class OrderDao implements IOrderDao {
    @Override
    public List<Order> getOrders() {
        return HibernateUtil.getAll("ORDER");
    }

    @Override
    public Order getOrder(int orderNumber) {
        return HibernateUtil.get(Order.class, orderNumber);
    }

    @Override
    public void updateOrder(Order order) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Order oldOrder = getOrder(order.getOrderID());
        oldOrder.setOrderProducts(order.getOrderProducts());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteOrder(int id) {
        HibernateUtil.delete(Order.class, id);
    }

    @Override
    public void deleteOrder(Order order) {
        deleteOrder(order.getOrderID());
    }
}
