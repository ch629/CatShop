package uk.ac.brighton.uni.ch629.catshop.data;

import java.io.Serializable;

public class OrderProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    private OrderProductId orderProductId;
    private int quantity;

    public OrderProduct() {
    }

    public OrderProduct(Order order, Product product, int quantity) {
        orderProductId = new OrderProductId(order, product);
        this.quantity = quantity;
    }

    public Product getProduct() {
        return orderProductId.getProduct();
    }

    public void setProduct(Product product) {
        this.orderProductId.setProduct(product);
    }

    public Order getOrder() {
        return orderProductId.getOrder();
    }

    public void setOrder(Order order) {
        this.orderProductId.setOrder(order);
    }

    public OrderProductId getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(OrderProductId orderProductId) {
        this.orderProductId = orderProductId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
