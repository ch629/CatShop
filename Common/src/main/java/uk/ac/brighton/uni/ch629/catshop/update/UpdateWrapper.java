package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class UpdateWrapper {
    private final Update update;

    @JsonCreator
    public UpdateWrapper(@JsonProperty("update") Update update) { //TODO: Trying this without the updateType String in the Wrapper, I should just be able to use getUpdateName to get the correct type when receiving this data.
        this.update = update;
    }

    @JsonIgnore
    public String getUpdateType() {
        return update.getType();
    }

    public Update getUpdate() {
        return update;
    }
}
