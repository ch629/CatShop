package uk.ac.brighton.uni.ch629.catshop.connections;

import uk.ac.brighton.uni.ch629.catshop.JsonHelper;
import uk.ac.brighton.uni.ch629.catshop.OrderUpdate;
import uk.ac.brighton.uni.ch629.catshop.Update;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class SubscriptionManager {
    public static final SubscriptionManager INSTANCE = new SubscriptionManager();
    private final Set<Subscription> productSubscriptions = new ConcurrentSkipListSet<>(), orderSubscriptions = new ConcurrentSkipListSet<>(); //Maybe use Synchronized List/Set. Separating these so that multiple things can happen at the same time.

    private SubscriptionManager() {
    }

    public static SubscriptionManager getInstance() {
        return INSTANCE;
    }

    private Set<Subscription> getSubscriptionSet(SubscriptionType type) {
        switch (type) {
            case PRODUCT:
                return productSubscriptions;
            case ORDER:
                return orderSubscriptions;
        }
        return null;
    }

    public void addSubscription(SubscriptionType type, Subscription subscription) {
        Set<Subscription> subscriptions = getSubscriptionSet(type);

        synchronized (Objects.requireNonNull(subscriptions)) { //This apparently works fine, as it still references the Set.
            subscriptions.add(subscription);
        }
    }

    public boolean removeSubscription(String ip, int port) {
        boolean ret;
        synchronized (productSubscriptions) {
            ret = productSubscriptions.removeIf(subscription -> subscription.getIP().equals(ip) && subscription.getPort() == port);
        }

        synchronized (orderSubscriptions) {
            boolean tmp = orderSubscriptions.removeIf(subscription -> subscription.getIP().equals(ip) && subscription.getPort() == port);
            if (!ret) ret = tmp;
        }
        return ret;
    }

    protected boolean timeoutSubscription(String ip, int port) {
        return removeSubscription(ip, port);
    }

    private void sendUpdateToSubscription(Subscription subscription, Update update) {
        try {
            Socket socket = new Socket(subscription.getIP(), subscription.getPort());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(JsonHelper.objectToNode(update).toString());
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdate(Update update) {
        if (update instanceof OrderUpdate) {
            synchronized (orderSubscriptions) {
                orderSubscriptions.forEach(subscription -> sendUpdateToSubscription(subscription, update));
            }
        } else {
            synchronized (productSubscriptions) {
                productSubscriptions.forEach(subscription -> sendUpdateToSubscription(subscription, update));
            }
        }
    }
}