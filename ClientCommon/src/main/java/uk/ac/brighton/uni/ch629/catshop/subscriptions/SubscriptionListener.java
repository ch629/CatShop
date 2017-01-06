package uk.ac.brighton.uni.ch629.catshop.subscriptions;

import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.update.UpdateWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SubscriptionListener implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private SubscriptionClientRunnable runnable;

    public SubscriptionListener(Socket socket, SubscriptionClientRunnable runnable) {
        try {
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.runnable = runnable;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Thread makeThread(Socket socket, SubscriptionClientRunnable runnable) {
        return new Thread(new SubscriptionListener(socket, runnable));
    }

    @Override
    public void run() {
        while (socket.isConnected() && !socket.isClosed()) {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    UpdateWrapper updateWrapper = JsonHelper.jsonToObject(line, UpdateWrapper.class);
                    runnable.onUpdateReceived(updateWrapper);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
        }
    }
}