package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddOrder.class, name = "AddOrder"),
        @JsonSubTypes.Type(value = PickOrder.class, name = "PickOrder"),
        @JsonSubTypes.Type(value = ShopDisplayUpdate.class, name = "ShopDisplayUpdate"),
        @JsonSubTypes.Type(value = AddOrderNew.class, name = "AddOrderNew"),
})
public interface Update {
    String getType();
}
