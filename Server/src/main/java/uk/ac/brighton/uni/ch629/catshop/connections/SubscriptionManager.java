package uk.ac.brighton.uni.ch629.catshop.connections;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import uk.ac.brighton.uni.ch629.catshop.update.Update;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class SubscriptionManager {
    public static final SubscriptionManager INSTANCE = new SubscriptionManager();
    private static final int TIMEOUT = 120;
    @Deprecated //TODO: Might need to keep this dependant on concurrency
    private final Map<Class<? extends Update>, List<Subscription>> subscriptions = new HashMap<>(); //TODO: Might need a Queue for tasks (LIFO)
    private final Set<Subscription> responses = new ConcurrentSkipListSet<>();
    private final Multimap<Class<? extends Update>, Subscription> subscriptionsNew = HashMultimap.create(); //HashSet values

    private SubscriptionManager() {
    }

    public static SubscriptionManager getInstance() {
        return INSTANCE;
    }

    public void addSubscription(Subscription subscription) {
        Arrays.stream(subscription.getTypes()).forEach(type -> subscriptions.get(type).add(subscription));
        Arrays.stream(subscription.getTypes()).forEach(type -> subscriptionsNew.put(type, subscription));
    }

    private boolean removeSubscriptions(String ip, int port) {
        boolean ret = false;

        synchronized (subscriptionsNew) {
            Multimap<Class<? extends Update>, Subscription> filteredSubs = Multimaps.filterEntries(subscriptionsNew, sub -> {
                assert sub != null;
                return sub.getValue().getIP().equals(ip) && sub.getValue().getPort() == port;
            });
            filteredSubs.entries().forEach(entry -> subscriptionsNew.remove(entry.getKey(), entry.getValue()));
        }

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

        synchronized (subscriptionsNew) {
            Arrays.stream(subscription.getTypes()).forEach(subType -> subscriptionsNew.remove(subType, subscription));
        }

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
        synchronized (subscriptionsNew) {
            subscriptionsNew.get(update.getType()).forEach(subscription -> subscription.sendUpdate(update));
        }

        synchronized (subscriptions) {
            List<Subscription> subs = subscriptions.get(update.getType());
            subs.forEach(sub -> sub.sendUpdate(update));
        }
    }
}