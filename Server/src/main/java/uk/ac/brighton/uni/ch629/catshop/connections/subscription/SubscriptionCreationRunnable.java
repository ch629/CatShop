package uk.ac.brighton.uni.ch629.catshop.connections.subscription;

import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.RequestSubscription;

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
                if (request != null)
                    SubscriptionManager.getInstance().addSubscription(request.getUpdateName(), new Subscription(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}