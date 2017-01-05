package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class UpdateWrapper { //TODO: Not sure if this is a great way to do this, but it's the simplest I can think of right now
    //TODO: Another way to do this would be to read it as a HashMap, get the type then cast appropriately
    private final Update update;

    @JsonCreator
    public UpdateWrapper(@JsonProperty("update") Update update) { //TODO: Trying this without the updateType String in the Wrapper, I should just be able to use getUpdateType to get the correct type when receiving this data.
        this.update = update;
    }

    public String getUpdateType() {
        return update.getType();
    }

    public Update getUpdate() {
        return update;
    }
}
