package uk.ac.brighton.uni.ch629.catshop.communication;

import com.google.gson.JsonObject;

public class Request {
    private String authToken;
    private JsonObject data;

    public Request(String authToken, JsonObject data) {
        this.authToken = authToken;
        this.data = data;
    }

    public static Request fromJson(JsonObject object) {
        return object.has("auth_token") ?
                new Request(
                        object.get("auth_token").getAsString(),
                        object.get("data").getAsJsonObject()
                ) : null;
    }

    public String getAuthToken() {
        return authToken;
    }

    public JsonObject getData() {
        return data;
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("auth_token", authToken);
        object.add("data", data);
        return object;
    }
}