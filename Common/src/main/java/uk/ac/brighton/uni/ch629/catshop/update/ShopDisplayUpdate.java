package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class ShopDisplayUpdate implements Update {
    private final int orderID;
    private final UpdateReason reason;

    @JsonCreator
    public ShopDisplayUpdate(@JsonProperty("orderID") int orderID,
                             @JsonProperty("reason") UpdateReason reason) {
        this.orderID = orderID;
        this.reason = reason;
    }

    @Override
    @JsonIgnore
    public String getType() {
        return "ShopDisplayUpdate";
    } //TODO: This may be deprecated.

    public int getOrderID() {
        return orderID;
    }

    public UpdateReason getReason() {
        return reason;
    }

    public enum UpdateReason {
        PICK, COLLECT, ADD
    }
}
