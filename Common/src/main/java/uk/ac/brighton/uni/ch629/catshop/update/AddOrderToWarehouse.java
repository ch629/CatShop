package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.data.Order;

@JsonAutoDetect
public class AddOrderToWarehouse implements Update {
    private final Order order;

    @JsonCreator
    public AddOrderToWarehouse(@JsonProperty("order") Order order) {
        this.order = order;
    }

    @Override
    @JsonIgnore
    public String getType() {
        return "AddOrderToWarehouse";
    }

    public Order getOrder() {
        return order;
    }
}
