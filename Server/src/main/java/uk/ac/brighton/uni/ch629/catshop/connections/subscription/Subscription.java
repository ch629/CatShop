package uk.ac.brighton.uni.ch629.catshop.connections.subscription;

import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.update.Update;
import uk.ac.brighton.uni.ch629.catshop.update.UpdateWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Subscription { //TODO: If I don't have enough time I could remove the response system
    private final Socket socket;
    private PrintWriter printWriter; //TODO: Check whether I should leave this open all the time or not.

    public Subscription(Socket socket) {
        this.socket = socket;
        try {
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendString(String string) {
        printWriter.println(string);
        printWriter.flush();
        printWriter.close();
    }

    public void sendUpdate(Update update) {
        if (!socket.isClosed()) {
            sendString(JsonHelper.objectToString(new UpdateWrapper(update)));
        } else
            SubscriptionManager.getInstance().removeSubscription(this); //Remove the Subscription if the Socket is closed.
    }
}

//TODO: The way this would usually work, is that the client would poll the server every x seconds to check for updates, rather than the server sending updates itself.