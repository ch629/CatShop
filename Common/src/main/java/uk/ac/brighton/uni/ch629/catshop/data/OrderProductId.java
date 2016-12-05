package uk.ac.brighton.uni.ch629.catshop.data;

import java.io.Serializable;

public class OrderProductId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Order order;
    private Product product;

    public OrderProductId() {
    }

    public OrderProductId(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
