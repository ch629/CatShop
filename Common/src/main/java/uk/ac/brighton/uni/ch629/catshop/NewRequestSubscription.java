package uk.ac.brighton.uni.ch629.catshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import uk.ac.brighton.uni.ch629.catshop.update.Update;

@JsonAutoDetect
public class NewRequestSubscription {
    private final String updateName; //TODO: Either this or an Enum, but this will be more expandable than an enum, which'll need manual modification for each type of update

    @JsonCreator
    public NewRequestSubscription(Class<? extends Update> updateClass) {
        this.updateName = updateClass.getSimpleName(); //TODO: Check whether I should use getSimpleName() or getName()
    }

    public String getUpdateType() {
        return updateName;
    }
}