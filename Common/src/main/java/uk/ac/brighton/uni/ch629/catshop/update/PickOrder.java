package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect
public class PickOrder implements Update { //Warehouse -> Server -> (ShopDisplay, Collection)
    private final int orderID;

    public PickOrder(int orderID) {
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