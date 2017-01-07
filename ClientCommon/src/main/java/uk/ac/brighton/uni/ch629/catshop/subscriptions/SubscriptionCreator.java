package uk.ac.brighton.uni.ch629.catshop.subscriptions;

import com.mashape.unirest.http.exceptions.UnirestException;
import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.RequestSubscription;
import uk.ac.brighton.uni.ch629.catshop.data.RequestUtil;
import uk.ac.brighton.uni.ch629.catshop.update.Update;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionCreator {
    public static int serverSubscriptionPort = -1; //TODO: This should probably be in Request or somewhere else, as SubscriptionCreator needs it.
    //TODO: Somewhere to store the hostname of the server & the port
    private static List<Socket> openSockets = new ArrayList<>();

    public SubscriptionCreator(Class<? extends Update> updateClass, SubscriptionClientRunnable runnable) {
        checkPort();
        try {
            Socket socket = new Socket("localhost", serverSubscriptionPort);
            RequestSubscription requestSubscription = new RequestSubscription(updateClass);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.println(JsonHelper.objectToString(requestSubscription));
            writer.flush();
//            writer.close(); //NOTE: This closes the socket

//            openSockets.add(socket);

            SubscriptionListener.makeThread(socket, runnable).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSubscriptionPort() {
        try {
            String port = RequestUtil.sendStringGet("/subscribe/port");
            serverSubscriptionPort = Integer.parseInt(port);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private void checkPort() {
        if (serverSubscriptionPort == -1) getSubscriptionPort();
    }
}