package uk.ac.brighton.uni.ch629.catshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.update.Update;

@JsonAutoDetect
public class RequestSubscription {
    private final String updateName; //TODO: Either this or an Enum, but this will be more expandable than an enum, which'll need manual modification for each type of update

    public RequestSubscription(Class<? extends Update> updateClass) {
        this.updateName = updateClass.getSimpleName(); //TODO: Check whether I should use getSimpleName() or getName()
    }

    @JsonCreator
    public RequestSubscription(@JsonProperty("updateName") String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateType() {
        return updateName;
    }
}