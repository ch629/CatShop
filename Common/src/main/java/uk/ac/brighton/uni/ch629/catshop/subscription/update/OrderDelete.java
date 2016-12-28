package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDelete extends Update {
    private final int orderID;

    @JsonCreator
    public OrderDelete(@JsonProperty("orderID") int orderID) {
        super(UpdateType.DELETE);
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }
}