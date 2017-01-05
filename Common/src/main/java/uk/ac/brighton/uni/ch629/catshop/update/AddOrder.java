package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import uk.ac.brighton.uni.ch629.catshop.data.Order;

@JsonAutoDetect
public class AddOrder implements Update { //Cashier -> Server -> Warehouse
    private final Order order;

    public AddOrder(Order order) {
        this.order = order;
    }

    @Override
    public String getType() {
        return "AddOrder";
    }

    public Order getOrder() {
        return order;
    }
}
