package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * A Request to be sent to the server.
 */
@JsonAutoDetect
public class Request<T extends Serializable> { //This will only really be serialized, and not created from json
    @JsonIgnore
    private static String defaultAuthToken = null;
    @JsonIgnore //Ignored so it uses the getAuthToken method.
    private String authToken;
    private T data;

    @JsonCreator
    public Request(String authToken, T data) {
        this.authToken = authToken;
        this.data = data;
    }

    @JsonGetter("authToken")
    public String getAuthToken() {
        if (defaultAuthToken != null) return defaultAuthToken;
        return authToken;
    }
}
