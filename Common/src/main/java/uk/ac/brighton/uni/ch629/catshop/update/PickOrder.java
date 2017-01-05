package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class PickOrder implements Update { //Warehouse -> Server -> (ShopDisplay, Collection)
    private int orderID;

    @Override
    public String getType() {
        return "PickOrder";
    }
}