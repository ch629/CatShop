package uk.ac.brighton.uni.ch629.catshop.connections;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Subscription { //NOTE: Could use RMI for this communication?
    private String ip;
    private int port;
    private SubscriptionType[] types; //Can have many types of connections to listen to

    public Subscription(String ip, int port, SubscriptionType... types) {
        this.ip = ip;
        this.port = port;
        this.types = types;
    }

    public void sendUpdate() { //TODO: Add Update Object
        throw new NotImplementedException();
    }

    public String getIP() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public SubscriptionType[] getTypes() {
        return types;
    }
}