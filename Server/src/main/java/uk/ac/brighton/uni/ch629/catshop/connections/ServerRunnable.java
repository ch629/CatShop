package uk.ac.brighton.uni.ch629.catshop.connections;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * The Runnable for the ServerSocket to accept new Client Sockets
 */
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
        while (true) {
            try {
                SubscriptionCreationRunnable.makeThread(serverSocket.accept());
            } catch (IOException e) {
                System.err.printf("Error when attempting to accept new Subscription Socket");
                e.printStackTrace();
            }
        }
    }
}