package uk.ac.brighton.uni.ch629.catshop.connections;

import uk.ac.brighton.uni.ch629.catshop.update.Update;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class SubscriptionManager {
    public static final SubscriptionManager INSTANCE = new SubscriptionManager();
    private static final int TIMEOUT = 120;
    private final Map<Class<? extends Update>, List<Subscription>> subscriptions = new HashMap<>(); //TODO: Might need a Queue for tasks (LIFO)
    private final Set<Subscription> responses = new ConcurrentSkipListSet<>();

    private SubscriptionManager() {
    }

    public static SubscriptionManager getInstance() {
        return INSTANCE;
    }

    public void addSubscription(Subscription subscription) {
        Arrays.stream(subscription.getTypes()).forEach(type -> subscriptions.get(type).add(subscription));
    }

    private boolean removeSubscriptions(String ip, int port) {
        boolean ret = false;

        synchronized (subscriptions) {
            for (Class<? extends Update> updateClass : subscriptions.keySet()) {
                boolean tmp = subscriptions.get(updateClass).removeIf(subscription -> subscription.getIP().equals(ip) && subscription.getPort() == port);
                if (!ret) ret = tmp;
            }
        }

        return ret;
    }

    private boolean removeSubscription(Subscription subscription) {
        boolean ret = false;

        synchronized (subscriptions) {
            for (Class<? extends Update> clazz : subscriptions.keySet()) {
                boolean tmp = subscriptions.get(clazz).remove(subscription);
                if (!ret) ret = tmp;
            }
        }
        return ret;
    }

    private void sendUpdateToSubscription(Subscription subscription, Update update) {
        subscription.sendUpdate(update);
        TTLRunnable.makeThread(TIMEOUT, () -> {
            if (!hadResponse(subscription)) removeSubscription(subscription);
        });
    }

    public void addResponse(Subscription subscription) {
        synchronized (responses) {
            responses.add(subscription);
        }
    }

    private boolean hadResponse(Subscription subscription) {
        return responses.remove(subscription);
    }

    private void sendUpdate(Update update) {
        synchronized (subscriptions) {
            List<Subscription> subs = subscriptions.get(update.getType());
            subs.forEach(sub -> sub.sendUpdate(update));
        }
    }
}