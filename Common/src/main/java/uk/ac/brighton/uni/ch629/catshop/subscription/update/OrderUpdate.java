package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.data.Order;

@JsonAutoDetect
public class OrderUpdate extends Update { //TODO: Needs to contain data about the Update, may need different ones, for example OrderDeleteUpdate, as this only needs the OrderID to be sent
    private final int oldID;
    private final Order newOrder;

    @JsonCreator
    public OrderUpdate(@JsonProperty("oldID") int oldID,
                       @JsonProperty("newOrder") Order newOrder) {
        super(UpdateType.UPDATE);
        this.oldID = oldID;
        this.newOrder = newOrder;
    }

    public int getOldID() {
        return oldID;
    }

    public Order getNewOrder() {
        return newOrder;
    }
}
