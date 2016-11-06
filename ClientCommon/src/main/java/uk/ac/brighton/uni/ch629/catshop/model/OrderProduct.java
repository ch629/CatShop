package uk.ac.brighton.uni.ch629.catshop.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonAutoDetect
public class OrderProduct {
    @JsonManagedReference //Might need to be JsonBackReference
            Order order;

    @JsonManagedReference
    Product product;
    int quantity;

    @JsonCreator
    public OrderProduct(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void changeQuantity(int amount) { //Increments or Decrements the quantity
        quantity += amount; //Maybe delete if quantity = 0? -> Might need to use the Observer pattern for that.
    }
}
