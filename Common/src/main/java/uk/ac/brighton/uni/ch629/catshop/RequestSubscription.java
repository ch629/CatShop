package uk.ac.brighton.uni.ch629.catshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.update.Update;

@JsonAutoDetect
public class RequestSubscription {
    private final String updateName;

    public RequestSubscription(Class<? extends Update> updateClass) {
        this.updateName = updateClass.getSimpleName();
    }

    @JsonCreator
    public RequestSubscription(@JsonProperty("updateName") String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateName() {
        return updateName;
    }
}