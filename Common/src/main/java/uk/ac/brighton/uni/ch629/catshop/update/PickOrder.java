package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect
public class PickOrder implements Update { //Warehouse -> Server -> (ShopDisplay, Collection)
    private int orderID;

    @JsonIgnore
    @Override
    public Class<? extends Update> getType() {
        return getClass();
    }
}