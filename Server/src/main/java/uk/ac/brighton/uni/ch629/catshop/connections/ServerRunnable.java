package uk.ac.brighton.uni.ch629.catshop.connections;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerRunnable implements Runnable {
    private ServerSocket serverSocket;

    public ServerRunnable(int port) {
        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) try {
            SubscriptionManager.getInstance().createSubscription(serverSocket.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}