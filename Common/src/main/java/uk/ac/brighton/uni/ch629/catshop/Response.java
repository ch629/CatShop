package uk.ac.brighton.uni.ch629.catshop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A Response from the Server
 */
@JsonAutoDetect
public class Response { //This will only be created from JSON and then read as this object.
    private int responseCode; //TODO: Make enum

    @JsonCreator
    private Response(int responseCode/*, Object data*/) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
