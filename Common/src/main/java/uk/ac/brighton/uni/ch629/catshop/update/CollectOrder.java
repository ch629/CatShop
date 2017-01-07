package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect
public class CollectOrder implements Update { //Collection -> Server -> ShopDisplay
    private final int orderID;

    public CollectOrder(int orderID) { //TODO: It may be better to do these with Composition, just have an OrderID and an Enum to notify what to do with the Order
        this.orderID = orderID;
    }

    @Override
    @JsonIgnore
    public String getType() {
        return "CollectOrder";
    }

    public int getOrderID() {
        return orderID;
    }
}
