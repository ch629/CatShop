package uk.ac.brighton.uni.ch629.catshop.data;

import java.io.Serializable;

public class OrderProductId implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderID;
    private int productNumber;

    public OrderProductId() {
    }

    public OrderProductId(int orderID, int productNumber) {
        this.orderID = orderID;
        this.productNumber = productNumber;
    }

    public OrderProductId(Order order, Product product) {
        this.orderID = order.getOrderID();
        this.productNumber = product.getProductNumber();
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }
}
