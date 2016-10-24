package uk.ac.brighton.uni.ch629.catshop.communication;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.util.Pair;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Subscription { //TODO: Find a better name
    public static Subscription INSTANCE = new Subscription();
    private final HashMap<SubscriptionType, Set<Pair<String, Integer>>> SUBSCRIPTIONS = new HashMap<>();

    private Subscription() {
        SUBSCRIPTIONS.put(SubscriptionType.PRODUCT, new HashSet<>());
        SUBSCRIPTIONS.put(SubscriptionType.ORDER, new HashSet<>());
    }
    /**
     * Add a new IP to the subscription
     *
     * @param ip   The IP to send data to
     * @param type The type of Data to send
     */
    public void subscribe(String ip, int port, SubscriptionType type) { //Port?
        SUBSCRIPTIONS.get(type).add(new Pair<>(ip, port));
    }

    /**
     * Sends the data to all ip's that are of the specific type
     *
     * @param type The type of subscription to send
     * @param data The data to send
     */
    public void sendData(SubscriptionType type, JsonNode data) { //Transfer over HTTP?
        SUBSCRIPTIONS.get(type).forEach(connection -> {
            try {
                Socket socket = new Socket(connection.getKey(), connection.getValue());
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.write(data.toString());
                out.flush();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}