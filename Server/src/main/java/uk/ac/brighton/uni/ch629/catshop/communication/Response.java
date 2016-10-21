package uk.ac.brighton.uni.ch629.catshop.communication;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Response { //TODO: Maybe put this in a separate library.
    private ResponseCode responseCode;
    private String responseMessage = "";
    private JsonElement data = null;

    private Response() {
    }

    public Response(ResponseCode code, JsonElement data) {
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

    public static Response fromJson(JsonObject object) {
        Response response = new Response();
        response.responseCode = ResponseCode.values()[object.get("response").getAsInt()];
        response.responseMessage = object.get("message").getAsString();
        response.data = object.get("data");
        return response;
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("response", responseCode.ordinal());
        object.addProperty("message", responseMessage);
        if (data != null) object.add("data", data);
        return object;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}