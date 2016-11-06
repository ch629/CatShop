package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A Response from the Server
 */
@JsonAutoDetect
public class Response { //This will only be created from JSON and then read as this object.
    private int responseCode; //TODO: Make enum
    private Object data; //NOTE: Check this. -> Need to update the server to send a response code with every return, not sure how to do that easily

    @JsonCreator
    private Response(int responseCode, Object data) {
        this.responseCode = responseCode;
        this.data = data;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Object getData() {
        return data;
    }

    public <T> T getDataAs(Class<T> clazz) {
        return ((T) data);
    }
}
