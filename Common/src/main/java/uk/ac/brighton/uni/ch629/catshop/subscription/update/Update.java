package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Update {
    private UpdateType type;

    @JsonCreator
    public Update(@JsonProperty("type") UpdateType type) {
        this.type = type;
    }

    public UpdateType getType() {
        return type;
    }
}
