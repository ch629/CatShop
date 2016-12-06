package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonAutoDetect
public class OrderProductId implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonBackReference
    private Order order;

    @JsonBackReference
    private Product product;

    public OrderProductId() {
    }

    @JsonCreator
    public OrderProductId(@JsonProperty("order") Order order,
                          @JsonProperty("product") Product product) {
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
