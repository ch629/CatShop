package uk.ac.brighton.uni.ch629.catshop.connections;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.Socket;
import java.util.Arrays;

public class Subscription {
    private SubscriptionType[] types; //Can have many types of connections to listen to
    private Socket socket;

    public Subscription(Socket socket, SubscriptionType... types) {
        this.socket = socket;
        this.types = types;
    }

    public void sendUpdate() { //TODO: Add Update Object
        throw new NotImplementedException();
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