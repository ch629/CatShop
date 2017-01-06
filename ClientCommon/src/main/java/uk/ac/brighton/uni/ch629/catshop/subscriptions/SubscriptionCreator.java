package uk.ac.brighton.uni.ch629.catshop.subscriptions;

import com.mashape.unirest.http.exceptions.UnirestException;
import uk.ac.brighton.uni.ch629.catshop.data.RequestUtil;

public class SubscriptionCreator {
    public static int serverSubscriptionPort = -1; //TODO: This should probably be in Request or somewhere else, as SubscriptionCreator needs it.

    public static void getSubscriptionPort() {
        try {
            String port = RequestUtil.sendStringGet("/subscribe/port");
            serverSubscriptionPort = Integer.parseInt(port);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private void checkPort() {
        if (serverSubscriptionPort == -1) getSubscriptionPort();
    }
}