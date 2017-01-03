package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.ac.brighton.uni.ch629.catshop.data.Order;

@JsonAutoDetect
public class AddOrder implements Update { //Cashier -> Server -> (Warehouse, ShopDisplay)
    private Order order;

    @JsonIgnore
    @Override
    public Class<? extends Update> getType() {
        return getClass();
    }
}
