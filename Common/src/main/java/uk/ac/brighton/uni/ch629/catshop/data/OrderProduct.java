package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

@JsonAutoDetect
public class OrderProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private OrderProductId orderProductId;
    private int quantity;

    public OrderProduct() {
    }

    @JsonCreator
    public OrderProduct(@JsonProperty("product") Product product,
                        @JsonProperty("quantity") int quantity) {
        this(null, product, quantity); //TODO: Check if this is needed anymore.
    }

    @JsonCreator
    public OrderProduct(@JsonProperty("order") Order order,
                        @JsonProperty("product") Product product,
                        @JsonProperty("quantity") int quantity) {
        orderProductId = new OrderProductId(order, product);
        this.quantity = quantity;
    }

    @JsonProperty("product") //NOTE: These should allow me to just ignore the composite key class.
    public Product getProduct() {
        return orderProductId.getProduct();
    }

    @JsonSetter("product")
    public void setProduct(Product product) {
        this.orderProductId.setProduct(product);
    }

    @JsonProperty("order")
    @JsonBackReference
    public Order getOrder() {
        return orderProductId.getOrder();
    }

    @JsonSetter("order")
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
