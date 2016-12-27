package uk.ac.brighton.uni.ch629.catshop.connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerRunnable implements Runnable {
    private BufferedReader in;
    private ServerSocket serverSocket;
    private List<String> inputBuffer = new ArrayList<>();

    public ServerRunnable(int port) {
        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerRunnable(InputStreamReader isr) {
        in = new BufferedReader(isr);
        //TODO: Need the IP address of the Client so I can find the Subscription.
    }

    @Override
    public void run() {
        while (true) {
            String line;
            try {
                line = in.readLine();
                if (line != null && !line.isEmpty()) inputBuffer.add(line);
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }
}
