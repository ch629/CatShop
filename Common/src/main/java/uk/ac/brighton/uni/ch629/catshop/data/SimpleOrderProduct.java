package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds a Product alongside it's Quantity with no relation to an Order.
 */
@JsonAutoDetect
public class SimpleOrderProduct {
    private final Product product;
    private int quantity;

    @JsonCreator
    public SimpleOrderProduct(@JsonProperty("product") Product product,
                              @JsonProperty("quantity") int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
