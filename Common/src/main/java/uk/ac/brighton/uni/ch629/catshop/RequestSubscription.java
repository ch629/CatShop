package uk.ac.brighton.uni.ch629.catshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.subscription.SubscriptionType;

@JsonAutoDetect
public class RequestSubscription {
    private final SubscriptionType[] types;

    @JsonCreator
    public RequestSubscription(@JsonProperty("subscriptionTypes") SubscriptionType... types) {
        this.types = types;
    }

    public SubscriptionType[] getTypes() {
        return types;
    }
}