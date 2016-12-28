package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDelete implements Update {
    private final int orderID;

    @JsonCreator
    public OrderDelete(@JsonProperty("orderID") int orderID) {
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    @Override
    public UpdateType getType() {
        return UpdateType.ORDER_DELETE;
    }
}