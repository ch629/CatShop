package uk.ac.brighton.uni.ch629.catshop.connections;

import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.update.Update;
import uk.ac.brighton.uni.ch629.catshop.update.UpdateWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class Subscription {
    private Class<? extends Update>[] subscriptionTypes;
    private Socket socket;

    @SafeVarargs
    public Subscription(Socket socket, Class<? extends Update>... types) {
        this.socket = socket;
        this.subscriptionTypes = types;
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

    public Class<? extends Update>[] getTypes() {
        return subscriptionTypes;
    }

    public boolean hasType(Class<? extends Update> type) {
        return Arrays.stream(subscriptionTypes).anyMatch(subType -> subType.equals(type));
    }
}