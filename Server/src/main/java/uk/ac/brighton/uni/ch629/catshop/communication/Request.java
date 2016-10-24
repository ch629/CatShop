package uk.ac.brighton.uni.ch629.catshop.communication;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import uk.ac.brighton.uni.ch629.catshop.JsonHelper;

@JsonAutoDetect
public class Request {
    @JsonProperty("auth_token")
    private String authToken;
    @JsonProperty("data")
    private JsonNode data;

    @JsonCreator
    public Request(@JsonProperty("auth_token") String authToken,
                   @JsonProperty("data") JsonNode data) {
        this.authToken = authToken;
        this.data = data;
    }

    public String getAuthToken() {
        return authToken;
    }

    public JsonNode getData() {
        return data;
    }

    @Override
    public String toString() {
        return JsonHelper.objectToNode(this).toString();
    }
}