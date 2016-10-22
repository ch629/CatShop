package uk.ac.brighton.uni.ch629.catshop.communication;

import com.google.gson.JsonObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Request {
    String auth_token;

    public JsonObject toJson() {
        throw new NotImplementedException();
    }

    public Request fromJson() {
        throw new NotImplementedException();
    }
}