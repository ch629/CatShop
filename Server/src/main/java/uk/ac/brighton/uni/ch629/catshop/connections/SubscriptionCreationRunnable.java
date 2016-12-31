package uk.ac.brighton.uni.ch629.catshop.connections;

import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.RequestSubscription;
import uk.ac.brighton.uni.ch629.catshop.subscription.SubscriptionType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SubscriptionCreationRunnable implements Runnable {
    private final Socket socket;

    public SubscriptionCreationRunnable(Socket socket) {
        this.socket = socket;
    }

    public static void makeThread(Socket socket) {
        new Thread(new SubscriptionCreationRunnable(socket)).start();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String in = reader.readLine();
            if (!in.isEmpty()) {
                RequestSubscription request = JsonHelper.jsonToObject(in, RequestSubscription.class);
                if (request != null) {
                    Subscription subscription = new Subscription(socket, request.getTypes());
                    for (SubscriptionType subscriptionType : request.getTypes())
                        SubscriptionManager.getInstance().addSubscription(subscriptionType, subscription);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}