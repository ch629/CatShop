package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

@JsonAutoDetect
public class OrderProduct implements Serializable { //TODO: This works fine with one constructor
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private OrderProductId orderProductId;
    private int quantity;

    public OrderProduct() {
    }

    @JsonIgnore
    public OrderProduct(@JsonProperty("order") Order order,
                        @JsonProperty("product") Product product,
                        @JsonProperty("quantity") int quantity) { //TODO: Check if this is ever used.
        orderProductId = new OrderProductId(order, product);
        this.quantity = quantity;
    }

    @JsonCreator
    public OrderProduct(@JsonProperty("product") Product product,
                        @JsonProperty("quantity") int quantity) {
        this(null, product, quantity);
    }

    public Product getProduct() {
        return orderProductId.getProduct();
    }

    public void setProduct(Product product) {
        this.orderProductId.setProduct(product);
    }

    @JsonBackReference
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
