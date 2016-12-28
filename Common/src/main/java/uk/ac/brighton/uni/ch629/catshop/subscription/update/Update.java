package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Update {
    @JsonIgnore
    UpdateType getType();
}
