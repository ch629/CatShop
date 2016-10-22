package uk.ac.brighton.uni.ch629.catshop.communication;

import com.google.gson.JsonObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

enum SubscriptionType {
    PRODUCT,
    ORDER
}

public class Subscription { //TODO: Find a better name
    /**
     * Add a new IP to the subscription
     *
     * @param ip   The IP to send data to
     * @param type The type of Data to send
     */
    public void subscribe(String ip, SubscriptionType type) { //Port?
        throw new NotImplementedException();
    }

    /**
     * Sends the data to all ip's that are of the specific type
     *
     * @param type The type of subscription to send
     * @param data The data to send
     */
    public void sendData(SubscriptionType type, JsonObject data) { //Transfer over HTTP?
        throw new NotImplementedException();
    }
}