package uk.ac.brighton.uni.ch629.catshop.connections;

import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.UpdateResponse;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RunnableUpdateResponse implements Runnable { //TODO: Rename this to something better
    //TODO: Could use a ServerSocket waiting for a response from the clients, would save making a lot of normal Sockets.
    private final int TIMEOUT = 100;
    private Socket socket;
    private String ip;
    private int port;

    public RunnableUpdateResponse(Subscription subscription) {
        this.ip = subscription.getIP();
        this.port = subscription.getPort();
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace(); //Probably shouldn't run if socket is null.
        }
    }

    @Override
    public void run() {
        try {
            socket.setSoTimeout(TIMEOUT);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String s = dis.readUTF();
            UpdateResponse response = JsonHelper.jsonToObject(s, UpdateResponse.class);
            socket.close();
            if (response != null) return; //Was a valid response
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubscriptionManager.getInstance().removeSubscription(ip, port); //Runs if no response.
    }
}