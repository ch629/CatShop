package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class UpdateWrapper { //TODO: Not sure if this is a great way to do this, but it's the simplest I can think of right now
    //TODO: Another way to do this would be to read it as a HashMap, get the type then cast appropriately
    private final Class<? extends Update> updateType;
    private final Update update;

    public UpdateWrapper(Update update) {
        this.update = update;
        this.updateType = update.getType();
    }

    @JsonCreator
    public UpdateWrapper(@JsonProperty("update") Update update,
                         @JsonProperty("updateType") Class<? extends Update> updateType) {
        this.update = update;
        this.updateType = updateType;
    }

    public Class<? extends Update> getUpdateType() {
        return updateType;
    }

    public Update getUpdate() {
        return update;
    }
}
