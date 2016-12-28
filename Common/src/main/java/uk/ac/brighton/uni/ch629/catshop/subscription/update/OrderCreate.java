package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.data.Order;

public class OrderCreate extends Update {
    private final Order order;

    @JsonCreator
    public OrderCreate(@JsonProperty("order") Order order) {
        super(UpdateType.CREATE);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}