package uk.ac.brighton.uni.ch629.catshop.data;

import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import uk.ac.brighton.uni.ch629.catshop.JsonHelper;

public class RequestUtil {
    public static final String serverURL = "localhost:8080/"; //TODO: Make a configuration file for this, could use Java Properties.

    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                return JsonHelper.jsonToObject(value, valueType);
            }

            @Override
            public String writeValue(Object value) {
                return JsonHelper.objectToNode(value).asText(); //Note: Not sure if this should be toString or asText?
            }
        });
    }

    public static Response sendGet(String url) throws UnirestException {
        return Unirest.get(serverURL + url).asObject(Response.class).getBody();
    }

    public static Response sendPost(Request request, String url) throws UnirestException {
        return Unirest.post(serverURL + url).body(request).asObject(Response.class).getBody();
    }

    public static Response sendDelete(Request request, String url) throws UnirestException {
        return Unirest.delete(serverURL + url).body(request).asObject(Response.class).getBody();
    }
}
