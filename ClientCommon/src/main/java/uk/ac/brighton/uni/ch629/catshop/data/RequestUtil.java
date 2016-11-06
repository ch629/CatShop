package uk.ac.brighton.uni.ch629.catshop.data;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class RequestUtil {
    public static final String serverURL = "localhost:8080/"; //TODO: Make a configuration file for this, could use Java Properties.

    public static Response sendGet(Request request, String url) throws IOException {
        StringBuilder sb = new StringBuilder();
        GetRequest getRequest = Unirest.get(serverURL + url);
        InputStream is = getRequest.getBody().getEntity().getContent();
        Scanner scanner = new Scanner(is);
        scanner.forEachRemaining(sb::append);
        return JsonHelper.jsonToObject(sb.toString(), Response.class); //TODO: Checks to avoid errors with converting to Response.
    }
}
