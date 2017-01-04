package uk.ac.brighton.uni.ch629.catshop.connections;

import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.update.Update;
import uk.ac.brighton.uni.ch629.catshop.update.UpdateWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Subscription { //TODO: This with the Set of UpdateTypes is only really needed to request the subscription, so I only really need to store the Socket in a Wrapper for dealing with them on the Server Side.
    private final Set<String> updateTypes; //NOTE: This is really like a listener
    private Socket socket;

    public Subscription(Socket socket, String updateType) {
        this.socket = socket;
        updateTypes = new HashSet<>();
        updateTypes.add(updateType);
    }

    public void sendUpdate(Update update) {
        try {
            UpdateWrapper wrapper = new UpdateWrapper(update);
            String json = JsonHelper.objectToNode(wrapper).toString();
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

    private synchronized void addUpdateType(String updateType) {
        updateTypes.add(updateType);
    }

    private synchronized boolean removeUpdateType(String updateType) {
        return updateTypes.remove(updateType);
    }

    public synchronized Set<String> getUpdateTypes() {
        return updateTypes;
    }

    public synchronized boolean hasType(String updateType) {
        return updateTypes.contains(updateType);
    }
}