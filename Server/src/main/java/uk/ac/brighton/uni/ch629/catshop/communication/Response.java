package uk.ac.brighton.uni.ch629.catshop.communication;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import uk.ac.brighton.uni.ch629.catshop.JsonHelper;


@JsonAutoDetect
public class Response { //TODO: Maybe put this in a separate library.
    @JsonProperty("responseCode")
    private ResponseCode responseCode; //TODO: Make converter for Jackson to serialize this to an integer
    @JsonIgnore
    private String responseMessage = ""; //TODO: This needed?
    @JsonProperty("data")
    private JsonNode data = null;

    private Response() {
    }

    @JsonCreator
    public Response(@JsonProperty("responseCode") ResponseCode code,
                    @JsonProperty("data") JsonNode data) {
        this.responseCode = code;
        this.data = data;
    }

    public Response(ResponseCode code) {
        this.responseCode = code;
    }

    public Response(ResponseCode code, String message) {
        this.responseCode = code;
        this.responseMessage = message;
    }

    public Response(ResponseCode code, String message, Object... args) {
        this.responseCode = code;
        this.responseMessage = String.format(message, args);
    }

    public JsonNode getData() {
        return data;
    }

    @Override
    public String toString() {
        return JsonHelper.objectToNode(this).toString();
    }
}