package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.data.Order;

@JsonAutoDetect
public class AddOrderToWarehouse implements Update { //TODO: There must be a better way to do this, but it'll do for now. This is a problem with Inheritance
    private final Order order;

    @JsonCreator
    public AddOrderToWarehouse(@JsonProperty("order") Order order) {
        this.order = order;
    }

    @Override
    public String getType() {
        return "AddOrderToWarehouse";
    }

    public Order getOrder() {
        return order;
    }
}
