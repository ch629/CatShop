package uk.ac.brighton.uni.ch629.catshop.connections;

import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.subscription.SubscriptionType;
import uk.ac.brighton.uni.ch629.catshop.subscription.update.Update;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class Subscription {
    private SubscriptionType[] types; //Can have many types of connections to listen to
    private Socket socket;

    public Subscription(Socket socket, SubscriptionType... types) {
        this.socket = socket;
        this.types = types;
    }

    public void sendUpdate(Update update) { //TODO: Add Update Object
        try {
            String json = JsonHelper.objectToNode(update).toString();
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(json);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIP() {
        return socket.getRemoteSocketAddress().toString(); //TODO: Check this is correct
    }

    public int getPort() {
        return socket.getPort();
    }

    public SubscriptionType[] getTypes() {
        return types;
    }

    public boolean hasType(SubscriptionType type) {
        return Arrays.stream(types).anyMatch(subscriptionType -> subscriptionType.equals(type));
    }
}