package uk.ac.brighton.uni.ch629.catshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.update.Update;

@JsonAutoDetect
@Deprecated
public class RequestSubscription {
    private final Class<? extends Update>[] subscriptionTypes;

    @SafeVarargs //TODO: Check this
    @JsonCreator
    public RequestSubscription(@JsonProperty("subscriptionTypes") Class<? extends Update>... types) { //TODO: Could require each client to request a singular subscription class, rahter  than allow it to request multiple(Most clients only need to listen to one anyways)
        subscriptionTypes = types;
    }

    public Class<? extends Update>[] getTypes() {
        return subscriptionTypes;
    }
}