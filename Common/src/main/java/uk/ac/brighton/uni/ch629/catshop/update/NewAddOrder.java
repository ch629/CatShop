package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.data.Order;

public class NewAddOrder implements Update {
    private final Order order;

    @JsonCreator
    public NewAddOrder(@JsonProperty("order") Order order) {
        this.order = order;
    }

    @Override
    @JsonIgnore
    public String getType() {
        return "AddOrder";
    }

    public Order getOrder() {
        return order;
    }
}
