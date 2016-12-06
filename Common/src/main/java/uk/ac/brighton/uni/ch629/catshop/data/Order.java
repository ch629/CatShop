package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@JsonAutoDetect
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderID;

    @JsonManagedReference
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Order() {
    }

    public Order(Map<Product, Integer> products) {
        products.entrySet().forEach(entry -> orderProducts.add(new OrderProduct(this, entry.getKey(), entry.getValue())));
    }

    @JsonCreator
    public Order(@JsonProperty("orderProducts") Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void addProduct(Product product, int quantity) {
        orderProducts.add(new OrderProduct(this, product, quantity));
    }

    public Set<ProductQuantity> getProducts() {
        Set<ProductQuantity> productQuantities = new HashSet<>();
        orderProducts.forEach(orderProduct -> productQuantities.add(new ProductQuantity(orderProduct.getProduct(), orderProduct.getQuantity())));
        return productQuantities;
    }
}