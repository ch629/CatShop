package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CollectOrder implements Update { //Collection -> Server -> ShopDisplay
    private int orderID;

    @Override
    public String getType() {
        return "CollectOrder";
    }
}
