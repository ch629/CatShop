package uk.ac.brighton.uni.ch629.catshop.connections;

import java.io.IOException;
import java.net.ServerSocket;

public class SubscriptionServer {
    private ServerSocket serverSocket;

    public SubscriptionServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.printf("Unable to create ServerSocket with Port: %d\n", port);
            e.printStackTrace();
        }


    }
}
