package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class AddOrderToShopDisplay implements Update {
    private final int orderID;

    @JsonCreator
    public AddOrderToShopDisplay(@JsonProperty("orderID") int orderID) {
        this.orderID = orderID;
    }

    @Override
    public String getType() {
        return "AddOrderToShopDisplay";
    }

    public int getOrderID() {
        return orderID;
    }
}
