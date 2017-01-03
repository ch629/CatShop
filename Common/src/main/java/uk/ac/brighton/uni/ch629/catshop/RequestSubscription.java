package uk.ac.brighton.uni.ch629.catshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.update.Update;

@JsonAutoDetect
public class RequestSubscription {
    private final Class<? extends Update>[] subscriptionTypes; //New Updated

    @SafeVarargs //TODO: Check this
    @JsonCreator
    public RequestSubscription(@JsonProperty("subscriptionTypes") Class<? extends Update>... types) {
        subscriptionTypes = types;
    }

    public Class<? extends Update>[] getTypes() {
        return subscriptionTypes;
    }
}