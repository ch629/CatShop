package uk.ac.brighton.uni.ch629.catshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

@JsonAutoDetect
public class OrderAddProduct { //This is so client's can add orders without direct access to the product data, can just use the ProductNumber and the OrderID. (Might not be useful because the client will probably have direct access to the Product and the Order before a product is added, because of the GUI).
    private int orderId, productId, quantity;

    @JsonCreator
    public OrderAddProduct(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
