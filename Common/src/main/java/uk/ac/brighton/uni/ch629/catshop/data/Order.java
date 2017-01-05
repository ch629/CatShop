package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.annotation.*;

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

    /*@JsonCreator
    public Order(@JsonProperty("orderID") int orderID,
                 @JsonProperty("orderProducts") Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
        this.orderID = orderID;
    }*/

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

    @JsonIgnore
    public Set<ProductQuantity> getProducts() {
        Set<ProductQuantity> productQuantities = new HashSet<>();
        orderProducts.forEach(orderProduct -> productQuantities.add(new ProductQuantity(orderProduct.getProduct(), orderProduct.getQuantity())));
        return productQuantities;
    }
}