package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class UpdateResponse {
    private final UpdateType updateType;
    private final Update update;

    @JsonCreator
    public UpdateResponse(@JsonProperty("updateType") UpdateType updateType,
                          @JsonProperty("update") Update update) {
        this.updateType = updateType;
        this.update = update;
    }

    public UpdateResponse(Update update) {
        this.updateType = update.getType();
        this.update = update;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public Update getUpdate() {
        return update;
    }
}