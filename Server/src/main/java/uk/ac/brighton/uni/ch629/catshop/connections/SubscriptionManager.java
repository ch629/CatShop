package uk.ac.brighton.uni.ch629.catshop.connections;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SubscriptionManager {
    public static final SubscriptionManager INSTANCE = new SubscriptionManager();
    private final Set<Subscription> productSubscriptions = new HashSet<>(), orderSubscriptions = new HashSet<>(); //Maybe use Synchronized List/Set. Separating these so that multiple things can happen at the same time.

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

    public void removeSubscription(String ip, int port) {
        synchronized (productSubscriptions) {
            productSubscriptions.removeIf(subscription -> subscription.getIP().equals(ip) && subscription.getPort() == port);
        }

        synchronized (orderSubscriptions) {
            orderSubscriptions.removeIf(subscription -> subscription.getIP().equals(ip) && subscription.getPort() == port);
        }
    }

    public void sendProductUpdate() { //TODO: Add object to be sent for the update
        synchronized (productSubscriptions) {
            throw new NotImplementedException();
        }
    }

    public void sendOrderUpdate() { //TODO: Add object to be sent for the update
        synchronized (orderSubscriptions) {
            throw new NotImplementedException();
        }
    }
}