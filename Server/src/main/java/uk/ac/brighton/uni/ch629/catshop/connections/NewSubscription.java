package uk.ac.brighton.uni.ch629.catshop.connections;

import java.net.InetAddress;
import java.net.Socket;

public class NewSubscription {
    private final Socket socket;

    public NewSubscription(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean equals(Object o) {
        assert o instanceof NewSubscription;
        NewSubscription otherNewSubscription = (NewSubscription) o;
        return getPort() == otherNewSubscription.getPort() && getInetAddress().equals(otherNewSubscription.getInetAddress());
    }

    @Override
    public int hashCode() {
        return socket != null ? socket.hashCode() : 0;
    }

    protected Socket getSocket() {
        return socket;
    }

    public int getPort() {
        return socket.getPort();
    }

    public InetAddress getInetAddress() {
        return socket.getInetAddress();
    }
}