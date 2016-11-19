package uk.ac.brighton.uni.ch629.catshop.connections;

import javafx.util.Pair;
import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.UpdateResponse;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerUpdateResponse implements Runnable {
    public static final int LISTEN_PORT = 8080;
    private final Set<Pair<String, Integer>> waitingForResponse = new HashSet<>(); //TODO: Something should probably be done in runnables, to allow the connections to timeout -> If this is done I will need to use synchronized methods/blocks to avoid multithreading issues.
    private final Set<RunnableResponseTimeout> timeouts = new HashSet<>();
    private ServerSocket serverSocket = null;
    private boolean isRunning = true;

    public ServerUpdateResponse() {
        try {
            serverSocket = new ServerSocket(LISTEN_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean removeResponse(String ip, int port) {
        synchronized (waitingForResponse) {
            return waitingForResponse.removeIf(response -> response.getKey().equals(ip) && response.getValue() == port);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                Pair<String, Integer> requester = new Pair<>(clientSocket.getRemoteSocketAddress().toString(), clientSocket.getPort());
                if (waitingForResponse.stream().anyMatch(response -> response.equals(requester))) { //Check whether we are waiting for a response from this client.
                    DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                    String input = dis.readUTF();
                    UpdateResponse response = JsonHelper.jsonToObject(input, UpdateResponse.class);
                    if (response != null) { //A valid response, could just be a string containing PONG for example. (Like the Ping/Pong system in IRC)
                        waitingForResponse.removeIf(x -> x.equals(requester));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
