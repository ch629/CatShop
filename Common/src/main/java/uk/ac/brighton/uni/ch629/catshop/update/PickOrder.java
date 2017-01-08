package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class PickOrder implements Update { //Warehouse -> Server -> Collection
    private final int orderID;

    @JsonCreator
    public PickOrder(@JsonProperty("orderID") int orderID) {
        this.orderID = orderID;
    }

    @Override
    @JsonIgnore
    public String getType() {
        return "PickOrder";
    }

    public int getOrderID() {
        return orderID;
    }
}